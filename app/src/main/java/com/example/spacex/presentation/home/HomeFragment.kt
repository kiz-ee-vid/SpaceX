package com.example.spacex.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.databinding.FragmentHomeBinding
import com.example.spacex.presentation.di.appComponent
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, vmFactory)[HomeViewModel::class.java]
    }
    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    lateinit var rocketAdapter: RocketAdapter
    private val rocketRecycler: RecyclerView by lazy { binding.rocketRecycler }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rocketAdapter = RocketAdapter() {
            val item = homeViewModel.allRockets.value?.get(it)
            if (item != null){
                val action = HomeFragmentDirections.actionNavigationHomeToNavigationRocket(item.mapToUiRocket())
                findNavController().navigate(action)
            }
            else{
                //TODO TOAST
            }
        }
        rocketRecycler.adapter = rocketAdapter
        rocketRecycler.layoutManager = LinearLayoutManager(context)

        homeViewModel.allRockets.observe(viewLifecycleOwner) {
            rocketAdapter.addList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}