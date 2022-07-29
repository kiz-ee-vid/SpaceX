package com.example.spacex.presentation.launchpads

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.databinding.FragmentLaunchesBinding
import com.example.spacex.databinding.FragmentLaunchpadsBinding
import com.example.spacex.presentation.di.appComponent
import java.util.ArrayList
import javax.inject.Inject

class LaunchpadsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val launchpadsViewModel: LaunchpadsViewModel by lazy {
        ViewModelProvider(this, vmFactory)[LaunchpadsViewModel::class.java]
    }
    private val binding: FragmentLaunchpadsBinding by lazy { FragmentLaunchpadsBinding.inflate(layoutInflater) }
    lateinit var launchpadAdapter: LaunchpadAdapter
    private val launchpadRecycler: RecyclerView by lazy { binding.launchpadsRecycler }
    private var filters = ArrayList(listOf("all", "active", "retired"))

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.radioGroup.clearCheck()
        when(launchpadsViewModel.filter){
            filters[0] -> {
                binding.allLaunchpads.isChecked = true
            }
            filters[1] -> {
                binding.activeLaunchpads.isChecked = true
            }
            filters[2] -> {
                binding.retiredLaunchpads.isChecked = true
            }
        }

        launchpadAdapter = LaunchpadAdapter() {
            val item = launchpadsViewModel.listLaunchpads.value?.get(it)
            if (item != null){
                val action = LaunchpadsFragmentDirections.actionNavigationLaunchpadsToNavigationLaunchpad(item)
                findNavController().navigate(action)
            }
            else{
                //TODO TOAST
            }
        }
        launchpadRecycler.adapter = launchpadAdapter
        launchpadRecycler.layoutManager = LinearLayoutManager(context)

        launchpadsViewModel.listLaunchpads.observe(viewLifecycleOwner) {
            launchpadAdapter.addList(it)
        }

        binding.allLaunchpads.setOnClickListener {
            launchpadsViewModel.filterByAll()
        }

        binding.activeLaunchpads.setOnClickListener {
            launchpadsViewModel.filterByActive()
        }

        binding.retiredLaunchpads.setOnClickListener {
            launchpadsViewModel.filterByRetired()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}