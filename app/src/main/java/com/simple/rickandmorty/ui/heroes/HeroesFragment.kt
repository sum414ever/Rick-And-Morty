package com.simple.rickandmorty.ui.heroes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simple.rickandmorty.databinding.FragmentHeroesBinding
import com.simple.rickandmorty.domain.entity.Hero
import com.simple.rickandmorty.ui.adapter.FingerprintAdapter
import com.simple.rickandmorty.ui.adapter.fingerprints.HeroesFingerprint
import com.simple.rickandmorty.ui.adapter.fingerprints.OnHeroSelect
import com.simple.rickandmorty.ui.adapter.fingerprints.TitleFingerprint
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroesFragment : Fragment(), OnHeroSelect {

    private var _binding: FragmentHeroesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FingerprintAdapter

    private val viewModel by viewModel<HeroesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleViewModel()
        adapter = FingerprintAdapter(getFingerprints())

        binding.apply {
            rvHeroes.layoutManager = LinearLayoutManager(this@HeroesFragment.context)
            rvHeroes.adapter = adapter

            rvHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val llm = recyclerView.layoutManager as LinearLayoutManager
                    if (llm.findLastVisibleItemPosition() > adapter.itemCount - 15 && !viewModel.viewStateLD.value?.isNetworking!!) {
                        viewModel.loadNextPage()
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    fun handleViewModel() {
        viewModel.heroesLD.observe(viewLifecycleOwner) { heroes ->
            adapter.setItems(heroes)
        }

        viewModel.viewStateLD.observe(viewLifecycleOwner) {
            if (it.isNetworking) {
                binding.pbHeroes.visibility = View.VISIBLE
            } else {
                binding.pbHeroes.visibility = View.INVISIBLE
            }
        }
    }

    private fun getFingerprints() = listOf(
        TitleFingerprint(),
        HeroesFingerprint(this)
    )

    override fun onSelected(hero: Hero) {
        var action = HeroesFragmentDirections.actionHeroesFragmentToSingleHeroFragment(hero)
        this.findNavController().navigate(action)
    }
}