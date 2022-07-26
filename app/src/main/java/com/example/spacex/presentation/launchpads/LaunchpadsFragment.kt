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
import com.example.spacex.presentation.launches.LaunchAdapter
import com.example.spacex.presentation.launches.LaunchesViewModel
import com.example.spacex.presentation.launches.launch.LaunchFragmentDirections
import java.util.ArrayList
import javax.inject.Inject

class LaunchpadsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val launchpadsViewModel: LaunchpadsViewModel by lazy {
        ViewModelProvider(this, vmFactory)[LaunchpadsViewModel::class.java]
    }
    private val binding: FragmentLaunchpadsBinding by lazy { FragmentLaunchpadsBinding.inflate(layoutInflater) }
    lateinit var launchAdapter: LaunchAdapter
    private val launchRecycler: RecyclerView by lazy { binding.launchpadsRecycler }
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

        launchAdapter = LaunchAdapter() {
//            val itemLaunch = launchpadsViewModel.listLaunchpads.value?.get(it)
//            if (itemLaunch != null && itemRocket != null){
//                val action = LaunchFragmentDirections.actionNavigationLaunchesToNavigationLaunch(itemLaunch)
//                findNavController().navigate(action)
//            }
//            else{
//                //TODO TOAST
//            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}