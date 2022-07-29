package com.example.spacex.presentation.launches.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacex.MainActivity
import com.example.spacex.R
import com.example.spacex.databinding.FragmentLaunchBinding
import com.example.spacex.presentation.home.rocket.ImageAdapter
import javax.inject.Inject

class LaunchFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    lateinit var imageAdapter: ImageAdapter
    private val imageRecycler: RecyclerView by lazy { binding.imageRecycler }
    private val binding: FragmentLaunchBinding by lazy {
        FragmentLaunchBinding.inflate(
            layoutInflater
        )
    }
    private val no = "No Data"

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as MainActivity).binding.navView.visibility = View.GONE
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: LaunchFragmentArgs by navArgs()
        val itemLaunch = args.itemLaunch
        val itemRocket = args.itemRocket
        binding.apply {
            Glide.with(launchImage)
                .load(itemLaunch.links?.patch?.large)
                .into(launchImage)

            launchTitle.text = itemLaunch.name
            launchDate.text = itemLaunch.dateUtc?.substring(0, 10) ?: "No data"
            if (itemLaunch.upcoming == false){
                upcoming.setImageResource(R.drawable.ic_completed)
            }
            else upcoming.setImageResource(R.drawable.ic_upcoming)
            number.text = "#".plus(itemLaunch.flightNumber)
            descript.text = itemLaunch.details ?: no
            staticLaunchDateRes.text = itemLaunch.staticFireDateUtc?.substring(0, 10) ?: "No fire date"
            dateLaunchRes.text = itemLaunch.dateUtc?.substring(0, 10) ?: "Upcoming"
            successRes.text = itemLaunch.success.toString()

            imageAdapter = ImageAdapter() {

            }
            imageAdapter.addList(itemRocket.flickr_images)
            imageRecycler.adapter = imageAdapter
            imageRecycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            Glide.with(rocketImage)
                .load(itemRocket.flickr_images[0])
                .into(rocketImage)
            rocketTittle.text = itemRocket.name
            rocketLaunch.text = itemRocket.first_flight
            rocketCost.text = itemRocket.cost_per_launch.toString()
            rocketSuccess.text = itemRocket.success_rate_pct.toString()

            wikipedia.setOnClickListener {
                if (itemLaunch.links?.wikipedia != null) {
                    val action =
                        LaunchFragmentDirections.actionNavigationLaunchToNavigationWeb(itemLaunch.links!!.wikipedia!!)
                    findNavController().navigate(action)
                } else
                    Toast.makeText(binding.root.context, "Nothing to show", Toast.LENGTH_SHORT).show()
            }

            youTube.setOnClickListener {
                if (itemLaunch.links?.webcast != null) {
                    val action =
                        LaunchFragmentDirections.actionNavigationLaunchToNavigationWeb(itemLaunch.links!!.webcast!!)
                    findNavController().navigate(action)
                } else
                    Toast.makeText(binding.root.context, "Nothing to show", Toast.LENGTH_SHORT).show()
            }

            recovery.setOnClickListener {
                if (itemLaunch.links?.reddit?.recovery != null) {
                    val action =
                        LaunchFragmentDirections.actionNavigationLaunchToNavigationWeb(itemLaunch.links!!.reddit!!.recovery!!)
                    findNavController().navigate(action)
                } else
                    Toast.makeText(binding.root.context, "Nothing to show", Toast.LENGTH_SHORT).show()
            }

            media.setOnClickListener {
                if (itemLaunch.links?.reddit?.media != null) {
                    val action =
                        LaunchFragmentDirections.actionNavigationLaunchToNavigationWeb(itemLaunch.links!!.reddit!!.media!!)
                    findNavController().navigate(action)
                } else
                    Toast.makeText(binding.root.context, "Nothing to show", Toast.LENGTH_SHORT).show()
            }

            recovery.setOnClickListener {
                if (itemLaunch.links?.reddit?.campaign != null) {
                    val action =
                        LaunchFragmentDirections.actionNavigationLaunchToNavigationWeb(itemLaunch.links!!.reddit!!.campaign!!)
                    findNavController().navigate(action)
                } else
                    Toast.makeText(binding.root.context, "Nothing to show", Toast.LENGTH_SHORT).show()
            }

            recovery.setOnClickListener {
                if (itemLaunch.links?.reddit?.launch != null) {
                    val action =
                        LaunchFragmentDirections.actionNavigationLaunchToNavigationWeb(itemLaunch.links!!.reddit!!.launch!!)
                    findNavController().navigate(action)
                } else
                    Toast.makeText(binding.root.context, "Nothing to show", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        (activity as MainActivity).binding.navView.visibility = View.VISIBLE
        super.onDestroyView()
    }
}