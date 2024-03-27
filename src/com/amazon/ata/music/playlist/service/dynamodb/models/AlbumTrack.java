package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Represents a record in the album_tracks table.
 */
@DynamoDBTable(tableName = "album_tracks")
public class AlbumTrack {

    @DynamoDBAttribute(attributeName="asin")
    @DynamoDBHashKey
    private String asin;

    @DynamoDBAttribute(attributeName="track_number")
    @DynamoDBRangeKey
    private Integer trackNumber;

    @DynamoDBAttribute(attributeName="album_name")
    private String albumName;

    @DynamoDBAttribute(attributeName="song_title")
    private String songTitle;

    public String getAsin() {
        return asin;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
}
