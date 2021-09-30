package com.simple.rickandmorty.ui.adapter.fingerprints

import android.content.res.Configuration
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.simple.rickandmorty.App
import com.simple.rickandmorty.R
import com.simple.rickandmorty.changeColorByTheme
import com.simple.rickandmorty.databinding.ItemHeroBinding
import com.simple.rickandmorty.domain.entity.Hero
import com.simple.rickandmorty.domain.entity.Item
import com.simple.rickandmorty.setRippleEffect
import com.simple.rickandmorty.ui.adapter.BaseViewHolder
import com.simple.rickandmorty.ui.adapter.ItemFingerprint
import com.simple.rickandmorty.ui.heroes.HeroesFragment

class HeroesFingerprint(var fragment: HeroesFragment) : ItemFingerprint<ItemHeroBinding, Hero> {
    override fun isRelativeItem(item: Item) = item is Hero

    override fun getLayoutId() = R.layout.item_hero

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemHeroBinding, Hero> {
        val binding = ItemHeroBinding.inflate(layoutInflater, parent, false)
        return HeroViewHolder(binding, fragment)
    }
}
interface OnHeroSelect {
    fun onSelected(hero: Hero)
}

class HeroViewHolder(binding: ItemHeroBinding, var fragment: HeroesFragment) : BaseViewHolder<ItemHeroBinding, Hero>(binding) {
    override fun onBind(hero: Hero) {
        binding.apply {
            Glide.with(App.applicationContext())
                .load(hero.image)
                .error(R.drawable.dummy)
                .into(ivHumanIcon)

            itemHero.setOnClickListener{
                fragment.onSelected(hero)
            }
            val currentNightMode = App.applicationContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            itemHero.setRippleEffect(currentNightMode)
            tvHumanGender.changeColorByTheme(currentNightMode)
            tvHumanName.changeColorByTheme(currentNightMode)
            tvHumanSpecies.changeColorByTheme(currentNightMode)
            tvHumanStatus.changeColorByTheme(currentNightMode)

            tvHumanGender.text = "Gender: ${hero.gender}"
            tvHumanName.text = hero.name
            tvHumanSpecies.text = "Species: ${hero.species}"
            tvHumanStatus.text = "Status: ${hero.status}"
        }
    }
}