package com.example.spacex.presentation.rockets.rocket

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
import com.example.spacex.data.model.Rocket
import com.example.spacex.databinding.FragmentRocketBinding
import javax.inject.Inject

class RocketFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    lateinit var imageAdapter: ImageAdapter
    private val imageRecycler: RecyclerView by lazy { binding.imageRecycler }
    private val binding: FragmentRocketBinding by lazy {
        FragmentRocketBinding.inflate(
            layoutInflater
        )
    }
    private var rocket: Rocket? = null
    val no = "No information"

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as MainActivity).binding.navView.visibility = View.GONE
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: RocketFragmentArgs by navArgs()
        val rocket = args.item
        binding.apply {
            Glide.with(image)
                .load(rocket.flickr_images[0])
                .into(image)
            descript.text = rocket.description ?: no
            firstLaunchRes.text = rocket.first_flight ?: no
            launchCostRes.text = rocket.cost_per_launch.toString().plus("$")
            successRes.text = rocket.success_rate_pct.toString().plus("%")
            massRes.text = rocket.mass?.kg.toString().plus(" kg")
            heightRes.text = rocket.height?.meters.toString().plus(" meters")
            diameterRes.text = rocket.diameter?.meters.toString().plus(" meters")

            typeRes.text = rocket.engines?.type ?: no
            layoutRes.text = rocket.engines?.layout ?: no
            versionRes.text = rocket.engines?.version ?: no
            amountRes.text = rocket.engines?.number.toString()
            propellant1Res.text = rocket.engines?.propellant1 ?: no
            propellant2Res.text = rocket.engines?.propellant2 ?: no

            reusableRes.text = rocket.first_stage?.reusable.toString()
            engineAmountRes.text = rocket.first_stage?.engines.toString()
            fuelAmountRes.text = rocket.first_stage?.fuel_amount_tons.toString().plus(" tons")
            burningTimeRes.text = rocket.first_stage?.burn_time_sec.toString().plus(" seconds")
            thrustSeaLevelRes.text = rocket.first_stage?.thrust_sea_level?.kN.toString().plus(" kN")
            thrustVacuumRes.text = rocket.first_stage?.thrust_vacuum?.kN.toString().plus(" kN")

            reusableSecondRes.text = rocket.second_stage?.reusable.toString()
            engineAmountSecondRes.text = rocket.second_stage?.engines.toString()
            fuelAmountRes.text = rocket.second_stage?.fuel_amount_tons.toString().plus(" tons")
            burningTimeSecondRes.text =
                rocket.second_stage?.burn_time_sec.toString().plus(" seconds")
            thrustRes.text = rocket.second_stage?.thrust?.kN.toString().plus(" kN")

            amountEndRes.text = rocket.landingLegs?.number.toString()
            materialRes.text = rocket.landingLegs?.material.toString()

            imageAdapter = ImageAdapter() {

            }
            imageAdapter.addList(rocket.flickr_images)
            imageRecycler.adapter = imageAdapter
            imageRecycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            wikipedia.setOnClickListener {
                if (rocket.wikipedia != null) {
                    val action =
                        RocketFragmentDirections.actionNavigationRocketToNavigationWeb(rocket.wikipedia!!)
                    findNavController().navigate(action)
                } else
                Toast.makeText(context, "Nothing to show", Toast.LENGTH_SHORT)
            }

        }

        return binding.root
    }

    override fun onDestroy() {
        (activity as MainActivity).binding.navView.visibility = View.VISIBLE
        super.onDestroy()
    }
}