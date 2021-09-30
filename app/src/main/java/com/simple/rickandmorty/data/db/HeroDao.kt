package com.simple.rickandmorty.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simple.rickandmorty.domain.entity.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): Flow<List<Hero>>

    @Query("SELECT * FROM heroes WHERE id BETWEEN :startRange AND :endRange")
    fun getPage(startRange: Int, endRange: Int): Flow<List<Hero>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<Hero>)

    @Query("DELETE FROM heroes")
    suspend fun deleteAllHeroes()
}