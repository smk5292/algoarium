package com.d204.algo.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
//
//    @Provides
//    @Singleton
//    fun provideRoomDatabase(@ApplicationContext context: Context): CharactersDatabase {
//        return CharactersDatabase.getInstance(context)
//    }
//
//    @Provides
//    @Singleton
//    fun provideCharacterDao(charactersDatabase: CharactersDatabase): CharacterDao {
//        return charactersDatabase.cachedCharacterDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideCharacterCache(characterCache: CharacterCacheImp): CharacterCache {
//        return characterCache
//    }
//
//    @Provides
//    @Singleton
//    fun providePreferenceHelper(@ApplicationContext context: Context): CachePreferencesHelper {
//        return CachePreferencesHelper(context)
//    }
}
