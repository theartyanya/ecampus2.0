package com.example.thear.ecampus20.dagger.module;

import com.example.thear.ecampus20.dagger.holder.TokenHolder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aleks on 14.05.2017.
 */
@Module
public class TokenModule {
    private TokenHolder tokenHolder;


    public TokenModule() {
        tokenHolder = new TokenHolder();
    }

    @Provides
    TokenHolder provideTokenHolder() {
        return tokenHolder;
    }
}
