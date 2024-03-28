package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.models.SongModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public static PlaylistModel toPlaylistModel(Playlist playlist) {
        return PlaylistModel.builder()
            .withId(playlist.getId())
            .build();
    }

    /**
     * Converts a provided {@link AlbumTrack} into a {@link SongModel}.
     * @param albumTrack the album track to convert
     * @return the converted song model
     */
    public static SongModel convertToSongModel(AlbumTrack albumTrack) {
        return new SongModel.Builder()
                .withAsin(albumTrack.getAsin())
                .withTitle(albumTrack.getSongTitle())
                .withAlbum(albumTrack.getAlbumName())
                .withTrackNumber(albumTrack.getTrackNumber())
                .build();
    }

    /**
     * Converts a list of {@link AlbumTrack} objects into a list of {@link SongModel} objects.
     * @param albumTracks the list of album tracks to convert
     * @return the list of converted song models
     */
    public static List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
        List<SongModel> songModels = new ArrayList<>();
        for (AlbumTrack albumTrack : albumTracks) {
            songModels.add(convertToSongModel(albumTrack));
        }
        return songModels;
    }
}
