package com.simple.rickandmorty.ui.single

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.simple.rickandmorty.App
import com.simple.rickandmorty.R
import com.simple.rickandmorty.changeColorByTheme
import com.simple.rickandmorty.databinding.FragmentSingleHeroBinding
import com.simple.rickandmorty.setRippleEffect

class SingleHeroFragment : Fragment() {

    private var _binding: FragmentSingleHeroBinding? = null
    private val binding get() = _binding!!
    private val args: SingleHeroFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ivHeroImage.clipToOutline = true

            val hero = args.item

            Glide.with(App.applicationContext())
                .load(hero.image)
                .error(R.drawable.dummy)
                .into(ivHeroImage)

            val currentNightMode = App.applicationContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}