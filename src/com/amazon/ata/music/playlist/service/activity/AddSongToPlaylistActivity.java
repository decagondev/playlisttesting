package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.AlbumTrackNotFoundException;
import com.amazon.ata.music.playlist.service.exceptions.PlaylistNotFoundException;
import com.amazon.ata.music.playlist.service.models.SongModel;
import com.amazon.ata.music.playlist.service.models.requests.AddSongToPlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.AddSongToPlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;


/**
 * Implementation of the AddSongToPlaylistActivity for the MusicPlaylistService's AddSongToPlaylist API.
 *
 * This API allows the customer to add a song to their existing playlist.
 */
public class AddSongToPlaylistActivity implements RequestHandler<AddSongToPlaylistRequest, AddSongToPlaylistResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;
    private final AlbumTrackDao albumTrackDao;

    /**
     * Instantiates a new AddSongToPlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     * @param albumTrackDao AlbumTrackDao to access the album_track table.
     */
    @Inject
    public AddSongToPlaylistActivity(PlaylistDao playlistDao, AlbumTrackDao albumTrackDao) {
        this.playlistDao = playlistDao;
        this.albumTrackDao = albumTrackDao;
    }

    /**
     * This method handles the incoming request by adding an additional song
     * to a playlist and persisting the updated playlist.
     * <p>
     * It then returns the updated song list of the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     * <p>
     * If the album track does not exist, this should throw an AlbumTrackNotFoundException.
     *
     * @param addSongToPlaylistRequest request object containing the playlist ID and an asin and track number
     *                                 to retrieve the song data
     * @return addSongToPlaylistResult result object containing the playlist's updated list of
     *                                 API defined {@link SongModel}s
     */
    @Override
    public AddSongToPlaylistResult handleRequest(final AddSongToPlaylistRequest request, Context context) {
        log.info("Received AddSongToPlaylistRequest: {}", request);

        AlbumTrack albumTrack = albumTrackDao.getAlbumTrack(request.getAsin(), request.getTrackNumber());
        if (albumTrack == null) {
            throw new AlbumTrackNotFoundException("Album track not found with ASIN: " + request.getAsin() + " and Track Number: " + request.getTrackNumber());
        }

        Playlist playlist = playlistDao.getPlaylist(request.getPlaylistId());
        if (playlist == null) {
            throw new PlaylistNotFoundException("Playlist not found with ID: " + request.getPlaylistId());
        }

        SongModel songModel = ModelConverter.convertToSongModel(albumTrack);
        ArrayList<AlbumTrack> updatedSongList = new ArrayList<>(playlist.getSongList());
        updatedSongList.add(albumTrack);

        playlist.setSongList(updatedSongList); // Assuming Playlist class has a setSongs method
        playlistDao.updatePlaylist(playlist); // Assuming updatePlaylist is implemented in PlaylistDao

        return AddSongToPlaylistResult.builder()
                .withSongList(updatedSongList)
                .build();
    }

    private SongModel convertAlbumTrackToSongModel(AlbumTrack albumTrack) {
        // Dummy conversion logic - replace with actual conversion logic
        return new SongModel.Builder()
                .withAsin(albumTrack.getAsin())
                .withTitle(albumTrack.getSongTitle())
                .withAlbum(albumTrack.getAlbumName())
                .withTrackNumber(albumTrack.getTrackNumber())
                .build();
    }
}