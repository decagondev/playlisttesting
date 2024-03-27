package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.music.playlist.service.activity.*;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.lambda.AddSongToPlaylistActivityProvider;
import com.amazon.ata.music.playlist.service.lambda.CreatePlaylistActivityProvider;
import com.amazon.ata.music.playlist.service.lambda.GetPlaylistActivityProvider;
import com.amazon.ata.music.playlist.service.lambda.UpdatePlaylistActivityProvider;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = DaoModule.class)
public interface ServiceComponent {
    //root object - Service
    PlaylistDao providePlaylistDao();

    void inject(GetPlaylistActivityProvider provider);
    void inject(CreatePlaylistActivityProvider provider);
    void inject(AddSongToPlaylistActivityProvider provider);
    void inject(UpdatePlaylistActivityProvider provider);

    AddSongToPlaylistActivity addSongToPlaylistActivity();
    CreatePlaylistActivity createPlaylistActivity();
    GetPlaylistActivity getPlaylistActivity();
    GetPlaylistSongsActivity getPlaylistSongActivity();
    UpdatePlaylistActivity updatePlaylistActivity();
}
