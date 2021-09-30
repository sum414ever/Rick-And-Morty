package com.simple.rickandmorty.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "heroes")
data class Hero(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val gender: String
) : Item, Parcelable