package com.simple.rickandmorty.data.net.adapter

typealias NetworkModel = com.simple.rickandmorty.data.net.entity.Hero
typealias DomainModel = com.simple.rickandmorty.domain.entity.Hero

object HeroModelAdapter : NetToDomainModelAdapter<NetworkModel, DomainModel>() {

    override fun toDomainModel(networkEntity: NetworkModel) = with(networkEntity) {
        DomainModel(
            id = id,
            name = name,
            status = status,
            species = species,
            image = image,
            gender = gender
        )
    }
}
