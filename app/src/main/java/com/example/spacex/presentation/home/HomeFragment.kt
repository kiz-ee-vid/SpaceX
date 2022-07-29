package com.example.spacex.presentation.home

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.system.Os.access
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.databinding.CustomDialogBinding
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
            if (item != null) {
                val action =
                    HomeFragmentDirections.actionNavigationHomeToNavigationRocket(item.mapToUiRocket())
                findNavController().navigate(action)
            } else {
                //TODO TOAST
            }
        }
        rocketRecycler.adapter = rocketAdapter
        rocketRecycler.layoutManager = LinearLayoutManager(context)
        val filterDialog = makeDialog()

        homeViewModel.allRockets.observe(viewLifecycleOwner) {
            rocketAdapter.addList(it)
        }

        binding.rocketsFilter.setOnClickListener {
            filterDialog.show()
        }
        return binding.root
    }

    private fun makeDialog(): Dialog {
        val builder = AlertDialog.Builder(binding.root.context)
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialogBinding.option1.text = "First launch"
        dialogBinding.option2.text = "Launch cost"
        dialogBinding.option3.text = "Success rate"
        builder.setView(dialogBinding.root)

            .setPositiveButton(
                R.string.access,
                DialogInterface.OnClickListener { _, _ ->
                    when (dialogBinding.optionsGroup.checkedRadioButtonId) {
                        dialogBinding.option1.id -> {
                            homeViewModel.filterByFirstLaunch()
                        }
                        dialogBinding.option2.id -> {
                            homeViewModel.filterByLaunchCost()
                        }
                        dialogBinding.option3.id -> {
                            homeViewModel.filterBySuccessRate()
                        }
                    }
                })
            .setNeutralButton(
                R.string.cancel,
                DialogInterface.OnClickListener { _, _ ->

                }
            )
        return builder.create()
    }
}