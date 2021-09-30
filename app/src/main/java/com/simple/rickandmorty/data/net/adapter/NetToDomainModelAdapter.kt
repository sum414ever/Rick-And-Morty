package com.simple.rickandmorty.data.net.adapter

abstract class NetToDomainModelAdapter<in N, out D> {

    abstract fun toDomainModel(networkEntity: N): D

    fun N.convertToDomainModel(): D = toDomainModel(this)

    fun List<N>.convertToDomainModel(): List<D> = ArrayList<D>(size).apply {
        this@convertToDomainModel.forEach { add(toDomainModel(it)) }
    }

}