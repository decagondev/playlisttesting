package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.models.SongModel;

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
}
