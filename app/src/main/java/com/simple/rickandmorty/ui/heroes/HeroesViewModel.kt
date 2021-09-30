package com.simple.rickandmorty.ui.heroes

import androidx.lifecycle.*
import com.simple.rickandmorty.domain.AppRepository
import com.simple.rickandmorty.domain.entity.Hero
import com.simple.rickandmorty.domain.entity.Item
import com.simple.rickandmorty.domain.entity.LoadStatus
import com.simple.rickandmorty.domain.entity.Title

class HeroesViewModel(private val repository: AppRepository) : ViewModel() {


    private val _newHeroesPageReloadTrigger = MutableLiveData<Long>(0)
    private val _heroes: LiveData<LoadStatus<List<Hero>>> =
        Transformations.switchMap(_newHeroesPageReloadTrigger) {
            repository.getHeroes(calculateNextPagePosition()).asLiveData()
        }

    private val _heroesLD = MutableLiveData<ArrayList<Item>>()
    val heroesLD: LiveData<ArrayList<Item>> = _heroesLD

    private val _viewStateLD = MutableLiveData<ViewState>()
    val viewStateLD: LiveData<ViewState> = _viewStateLD

    init {
        _heroes.observeForever { loadStatus ->
            when (loadStatus) {
                is LoadStatus.Loading ->
                    _viewStateLD.value = ViewState(isNetworking = true)
                is LoadStatus.Error -> _viewStateLD.value =
                    ViewState(errorMessage = loadStatus.message)
                is LoadStatus.Success -> {
                    _viewStateLD.value = ViewState(isNetworking = false)
                    if (_heroesLD.value.isNullOrEmpty()) {
                        _heroesLD.value = arrayListOf(Title("This is a list of all characters"))
                        loadStatus.data?.let { _heroesLD.value?.addAll(it) }
                        _heroesLD.value = _heroesLD.value
                    } else {
                        loadStatus.data?.let { _heroesLD.value?.addAll(it) }
                        _heroesLD.value = _heroesLD.value
                    }
                }
            }
        }
        loadNextPage()
    }

    fun loadNextPage() {
        _newHeroesPageReloadTrigger.value = System.currentTimeMillis()
    }

    private fun calculateNextPagePosition(): Int = when (_heroesLD.value?.isEmpty() ?: true) {
        true -> 1
        false -> _heroesLD.value!!.size / 20 + 1
    }


    data class ViewState(
        val isNetworking: Boolean = false,
        val errorMessage: String? = null
    )
}