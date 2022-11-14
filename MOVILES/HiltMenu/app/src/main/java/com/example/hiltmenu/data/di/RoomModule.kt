package com.example.hiltmenu.data.di

import android.content.Context
import androidx.room.Room
import com.example.roomviewmodel.data.PersonaRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Named("assetDB")
    fun getAssetDB() = "database/personas.db"


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("assetDB") ruta: String
    )  =
        Room.databaseBuilder(context, PersonaRoomDatabase::class.java, "persona_database")
            .fallbackToDestructiveMigration()
            .createFromAsset(ruta)
            .build()

    @Provides
    fun providesPersonaDao(articlesDatabase: PersonaRoomDatabase) =
        articlesDatabase.personaDao()


}

