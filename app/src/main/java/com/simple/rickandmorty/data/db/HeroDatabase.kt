package com.simple.rickandmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.simple.rickandmorty.domain.entity.Hero

@Database(entities = [Hero::class], version = 1)
abstract class HeroDatabase : RoomDatabase() {

    abstract fun heroesDao(): HeroDao
}