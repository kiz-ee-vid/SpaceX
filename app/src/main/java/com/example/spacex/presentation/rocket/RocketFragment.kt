package com.example.spacex.presentation.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.data.model.Rocket
import com.example.spacex.databinding.FragmentRocketBinding
import javax.inject.Inject

class RocketFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val rocketViewModel: RocketViewModel by lazy {
        ViewModelProvider(this, vmFactory)[RocketViewModel::class.java]
    }
    lateinit var imageAdapter: ImageAdapter
    private val imageRecycler: RecyclerView by lazy { binding.imageRecycler }
    private val binding: FragmentRocketBinding by lazy {
        FragmentRocketBinding.inflate(
            layoutInflater
        )
    }
    private var rocket: Rocket? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding.apply {
//            rocket = arguments?.getParcelable("rocket")
//            descript.text = rocket?.description ?: "No information"
//            firstLaunchRes.text = rocket?.first_flight ?: "No information"
//            launchCostRes.text = rocket?.cost_per_launch.toString().plus("$")
//            successRes.text = rocket?.success_rate_pct.toString().plus("%")
//            massRes.text = rocket?.mass?.kg.toString().plus(" kg")
//            heightRes.text = rocket?.height?.meters.toString().plus(" meters")
//            diameterRes.text = rocket?.diameter?.meters.toString().plus(" meters")
//
//            if (rocket != null) {
//                imageAdapter = ImageAdapter() {
//
//                }
//                imageAdapter.addList(rocket!!.flickr_images)
//                imageRecycler.adapter = imageAdapter
//                imageRecycler.layoutManager =
//                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//            }else{
//                images.visibility = View.GONE
//                imageRecycler.visibility = View.GONE
//            }
//
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}