package com.simple.rickandmorty.domain

import com.simple.rickandmorty.domain.entity.Hero
import com.simple.rickandmorty.domain.entity.LoadStatus
import kotlinx.coroutines.flow.Flow

interface AppRepository {

   fun getHeroes(page: Int) : Flow<LoadStatus<List<Hero>>>
}