package com.simple.rickandmorty.data.net

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.room.withTransaction
import com.simple.rickandmorty.data.db.HeroDatabase
import com.simple.rickandmorty.data.net.adapter.HeroModelAdapter
import com.simple.rickandmorty.domain.AppRepository
import com.simple.rickandmorty.networkBoundResource

class AppRepositoryImpl(private val apiService: ApiService, private val db: HeroDatabase) :
    AppRepository {

    private val heroesDao = db.heroesDao()

    override fun getHeroes(page: Int) = networkBoundResource(
        query = {
            heroesDao.getPage(page * 20 - 19, page * 20)
        },
        fetch = {
            with(HeroModelAdapter) {
                apiService.getHeroesList(page).heroes?.convertToDomainModel() ?: emptyList()
            }
        },
        saveFetchResult = { heroes ->
            db.withTransaction {
//                heroesDao.deleteAllHeroes()
                heroesDao.insertHeroes(heroes)
            }
        }
    )
}