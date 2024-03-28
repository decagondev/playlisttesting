package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazon.ata.music.playlist.service.util.MusicPlaylistServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.UUID;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 *
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreatePlaylistActivity implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;
    private final ModelConverter modelConverter;

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlists table.
     */
//    @Inject
//    public CreatePlaylistActivity(PlaylistDao playlistDao) {
//        this(playlistDao, new ModelConverter());
//    }

    @Inject
    public CreatePlaylistActivity(PlaylistDao playlistDao, ModelConverter modelConverter) {
        this.playlistDao = playlistDao;
        this.modelConverter = modelConverter;
    }
    /**
     * This method handles the incoming request by persisting a new playlist
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createPlaylistRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    @Override
    public CreatePlaylistResult handleRequest(final CreatePlaylistRequest createPlaylistRequest, Context context) {
        log.info("Received CreatePlaylistRequest {}", createPlaylistRequest);

        if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getName()) ||
            !MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getCustomerId()))    {
            throw new InvalidAttributeValueException("Invalid playlist name or customerID.");
        }

        String uniquePlaylistId = generateUniqueId();

        Playlist newPlaylist = new Playlist();
        newPlaylist.setId(uniquePlaylistId);
        newPlaylist.setName(createPlaylistRequest.getName());
        newPlaylist.setCustomerId(createPlaylistRequest.getCustomerId());
        newPlaylist.setTags(new HashSet<>(createPlaylistRequest.getTags()));

        playlistDao.savePlaylist(newPlaylist);

        PlaylistModel playlistModel = ModelConverter.toPlaylistModel(newPlaylist);
        return CreatePlaylistResult.builder()
                .withPlaylist(new PlaylistModel())
                .build();
    }
}
