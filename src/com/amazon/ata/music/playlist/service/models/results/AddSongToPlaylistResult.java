package com.amazon.ata.music.playlist.service.models.results;

import com.amazon.ata.music.playlist.service.models.SongModel;

import java.util.ArrayList;

public class AddSongToPlaylistResult {
    private final ArrayList<SongModel> songList;
    private final String message; // Example of an additional field

    private AddSongToPlaylistResult(Builder builder) {
        this.songList = builder.songList;
        this.message = builder.message; // Initialize in constructor for immutability
    }

    public ArrayList<SongModel> getSongList() {
        return songList;
    }

    public String getMessage() {
        return message;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private ArrayList<SongModel> songList;
        private String message;

        public Builder withSongList(ArrayList<SongModel> songListToUse) {
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