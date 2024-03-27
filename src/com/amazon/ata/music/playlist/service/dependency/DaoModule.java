package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DaoModule {

    @Provides
    @Singleton
    public DynamoDBMapper provideDynamoDBMapper() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .build();
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Provides
    @Singleton
    public ModelConverter provideModelConverter() {
        return new ModelConverter();
    }
}
