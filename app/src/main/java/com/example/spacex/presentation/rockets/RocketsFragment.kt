package com.example.spacex.presentation.rockets

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.databinding.CustomDialogBinding
import com.example.spacex.databinding.FragmentRocketsBinding
import com.example.spacex.presentation.di.appComponent
import javax.inject.Inject

class RocketsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val homeViewModel: RocketsViewModel by lazy {
        ViewModelProvider(this, vmFactory)[RocketsViewModel::class.java]
    }
    private val binding: FragmentRocketsBinding by lazy { FragmentRocketsBinding.inflate(layoutInflater) }
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
                    RocketsFragmentDirections.actionNavigationHomeToNavigationRocket(item.mapToUiRocket())
                findNavController().navigate(action)
            } else {
                Toast.makeText(binding.root.context, "Nothing to show", Toast.LENGTH_SHORT).show()
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
            .setCancelable(false)
            .setPositiveButton(
                R.string.access,
                DialogInterface.OnClickListener { _, _ ->
                    when (dialogBinding.optionsGroup.checkedRadioButtonId) {
                        dialogBinding.option1.id -> {
                            homeViewModel.sortByFirstLaunch()
                        }
                        dialogBinding.option2.id -> {
                            homeViewModel.sortByLaunchCost()
                        }
                        dialogBinding.option3.id -> {
                            homeViewModel.sortBySuccessRate()
                        }
                    }
                    dialogBinding.optionsGroup.clearCheck()
                })
            .setNeutralButton(
                R.string.cancel,
                DialogInterface.OnClickListener { _, _ ->
                    dialogBinding.optionsGroup.clearCheck()
                }
            )
        return builder.create()
    }
}