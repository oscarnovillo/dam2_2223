package com.example.roomviewmodel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hiltmenu.data.Converters
import com.example.hiltmenu.data.PersonaDao
import com.example.hiltmenu.data.modelo.CosaEntity
import com.example.hiltmenu.data.modelo.PersonaEntity

@Database(entities = [PersonaEntity::class, CosaEntity::class], version =6, exportSchema = true)
@TypeConverters(Converters::class)
abstract class PersonaRoomDatabase : RoomDatabase() {

    abstract fun personaDao(): PersonaDao

}