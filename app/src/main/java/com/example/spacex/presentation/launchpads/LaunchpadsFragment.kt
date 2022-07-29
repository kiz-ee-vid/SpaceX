package com.example.spacex.presentation.launchpads

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
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
        val filterDialog = makeDialog()

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

        binding.launchpadsFilter.setOnClickListener {
            filterDialog.show()
        }

        return binding.root
    }

    private fun makeDialog(): Dialog {
        val builder = AlertDialog.Builder(binding.root.context)
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialogBinding.option1.text = "Title"
        dialogBinding.option2.text = "Region"
        dialogBinding.option3.visibility = View.GONE
        builder.setView(dialogBinding.root)

            .setPositiveButton(
                R.string.access,
                DialogInterface.OnClickListener { _, _ ->
                    when (dialogBinding.optionsGroup.checkedRadioButtonId) {
                        dialogBinding.option1.id -> {
                            launchpadsViewModel.sortByTitle()
                        }
                        dialogBinding.option2.id -> {
                            launchpadsViewModel.sortByRegion()
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