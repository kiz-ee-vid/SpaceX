package com.example.spacex.presentation.launches

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.databinding.FragmentHomeBinding
import com.example.spacex.databinding.FragmentLaunchesBinding
import com.example.spacex.databinding.FragmentNotificationsBinding
import com.example.spacex.presentation.di.appComponent
import com.example.spacex.presentation.home.HomeFragmentDirections
import com.example.spacex.presentation.home.HomeViewModel
import com.example.spacex.presentation.home.RocketAdapter
import java.util.ArrayList
import javax.inject.Inject

class LaunchesFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val launchesViewModel: LaunchesViewModel by lazy {
        ViewModelProvider(this, vmFactory)[LaunchesViewModel::class.java]
    }
    private val binding: FragmentLaunchesBinding by lazy { FragmentLaunchesBinding.inflate(layoutInflater) }
    lateinit var launchAdapter: LaunchAdapter
    private val launchRecycler: RecyclerView by lazy { binding.launchRecycler }
    private var filters = ArrayList(listOf("All", "Past", "Future"))

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
        when(launchesViewModel.filter){
            filters[0] -> {
                binding.allRadioButton.isChecked = true
            }
            filters[1] -> {
                binding.pastRadioButton.isChecked = true
            }
            filters[2] -> {
                binding.futureRadioButton.isChecked = true
            }
        }
        launchAdapter = LaunchAdapter() {
//            val item = launchesViewModel.allLaunches.value?.get(it)
//            if (item != null){
//                val action = LaunchFragmentDirections.actionNavigationHomeToNavigationRocket(item.mapToUiLaunch())
//                findNavController().navigate(action)
//            }
//            else{
//                //TODO TOAST
//            }
        }
        launchRecycler.adapter = launchAdapter
        launchRecycler.layoutManager = LinearLayoutManager(context)

        launchesViewModel.listLaunches.observe(viewLifecycleOwner) {
            launchAdapter.addList(it)
        }

        binding.allRadioButton.setOnClickListener {
            launchesViewModel.filterByAll()
        }

        binding.pastRadioButton.setOnClickListener {
            launchesViewModel.filterByPast()
        }

        binding.futureRadioButton.setOnClickListener {
            launchesViewModel.filterByFuture()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}