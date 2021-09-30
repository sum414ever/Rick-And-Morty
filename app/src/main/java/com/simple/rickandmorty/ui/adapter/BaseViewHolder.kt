package com.simple.rickandmorty.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.simple.rickandmorty.domain.entity.Item

abstract class BaseViewHolder<out V : ViewBinding, in I : Item>(
    val binding: V
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun onBind(item: I)
}