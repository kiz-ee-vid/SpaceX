package com.example.spacex.presentation.launches

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
import com.example.spacex.databinding.FragmentLaunchesBinding
import com.example.spacex.presentation.di.appComponent
import com.example.spacex.presentation.launches.launch.LaunchFragmentDirections
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
            val itemLaunch = launchesViewModel.listLaunches.value?.get(it)
            val itemRocket = launchesViewModel.allRockets.find { rocket -> rocket.id.equals(itemLaunch?.rocket) }
            if (itemLaunch != null && itemRocket != null){
                val action = LaunchFragmentDirections.actionNavigationLaunchesToNavigationLaunch(itemLaunch, itemRocket)
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(binding.root.context, "Nothing to show", Toast.LENGTH_SHORT).show()
            }
        }
        launchRecycler.adapter = launchAdapter
        launchRecycler.layoutManager = LinearLayoutManager(context)
        val filterDialog = makeDialog()

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

        binding.launchesFilter.setOnClickListener {
            filterDialog.show()
        }

        return binding.root
    }

    private fun makeDialog(): Dialog {
        val builder = AlertDialog.Builder(binding.root.context)
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialogBinding.option1.text = "Launch date"
        dialogBinding.option2.text = "Title"
        dialogBinding.option3.text = "Date"
        builder.setView(dialogBinding.root)

            .setPositiveButton(
                R.string.access,
                DialogInterface.OnClickListener { _, _ ->
                    when (dialogBinding.optionsGroup.checkedRadioButtonId) {
                        dialogBinding.option1.id -> {
                            launchesViewModel.sortByLaunchDate()
                        }
                        dialogBinding.option2.id -> {
                            launchesViewModel.sortByTitle()
                        }
                        dialogBinding.option3.id -> {
                            launchesViewModel.sortByDate()
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
        dialogBinding.optionsGroup.clearCheck()
        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}