package com.simple.rickandmorty.ui.adapter.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import com.simple.rickandmorty.R
import com.simple.rickandmorty.databinding.ItemTitleBinding
import com.simple.rickandmorty.domain.entity.Item
import com.simple.rickandmorty.domain.entity.Title
import com.simple.rickandmorty.ui.adapter.BaseViewHolder
import com.simple.rickandmorty.ui.adapter.ItemFingerprint

class TitleFingerprint : ItemFingerprint<ItemTitleBinding, Title> {

    override fun isRelativeItem(item: Item) = item is Title

    override fun getLayoutId() = R.layout.item_title

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemTitleBinding, Title> {
        val binding = ItemTitleBinding.inflate(layoutInflater, parent, false)
        return TitleViewHolder(binding)
    }
}

class TitleViewHolder(binding: ItemTitleBinding) :
    BaseViewHolder<ItemTitleBinding, Title>(binding) {
    override fun onBind(item: Title) {
        binding.tvItemTitle.text = item.title
    }
}