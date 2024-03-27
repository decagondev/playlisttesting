package com.amazon.ata.music.playlist.service.exceptions;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

public class PlaylistServiceException extends RuntimeException{
    public PlaylistServiceException() {
        super(); //No-argument exception
    }

    public PlaylistServiceException(String message) {
        super(message); //construction with a message
    }

    public PlaylistServiceException(Throwable cause) {
        super(cause);
    }

    public PlaylistServiceException(String message, Throwable cause) {
        super(message, cause); //constructor with a message and cause
    }
}
