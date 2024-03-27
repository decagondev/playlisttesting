package com.amazon.ata.music.playlist.service.models.results;

import com.amazon.ata.music.playlist.service.models.SongModel;

import java.util.List;

public class AddSongToPlaylistResult {
    private final List<SongModel> songList;
    private final String message; // Example of an additional field

    private AddSongToPlaylistResult(Builder builder) {
        this.songList = builder.songList;
        this.message = builder.message; // Initialize in constructor for immutability
    }

    public List<SongModel> getSongList() {
        return songList;
    }

    public String getMessage() {
        return message;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private List<SongModel> songList;
        private String message;

        public Builder withSongList(List<SongModel> songListToUse) {
            this.songList = songListToUse;
            return this;
        }

        public Builder withMessage(String messageToUse) {
            this.message = messageToUse;
            return this;
        }

        public AddSongToPlaylistResult build() {
            if (songList == null || songList.isEmpty()) {
                throw new IllegalStateException("Song list cannot be null or empty.");
            }
            return new AddSongToPlaylistResult(this);
        }
    }
}