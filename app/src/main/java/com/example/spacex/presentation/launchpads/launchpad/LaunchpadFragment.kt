package com.example.spacex.presentation.launchpads.launchpad

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.MainActivity
import com.example.spacex.databinding.FragmentLaunchpadBinding
import com.example.spacex.domain.ui_model.UiLaunchpad
import com.example.spacex.presentation.rockets.rocket.ImageAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

class LaunchpadFragment : Fragment(), OnMapReadyCallback {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var imageAdapter: ImageAdapter
    private val binding: FragmentLaunchpadBinding by lazy {
        FragmentLaunchpadBinding.inflate(
            layoutInflater
        )
    }
    private val no = "No Data"
    private lateinit var nmap: GoogleMap
    private lateinit var mapView: CustomMapView
    private lateinit var item: UiLaunchpad

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as MainActivity).binding.navView.visibility = View.GONE
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: LaunchpadFragmentArgs by navArgs()
        item = args.item
        binding.apply {
            launchpadTitle.text = item.name
            launchpadFullName.text = item.fullName
            status.text = item.status
            descriptionRes.text = item.details
            regionRes.text = item.region
            locationRes.text = item.region
            launchAttemptsRes.text = item.launchAttempts.toString()


            imageAdapter = ImageAdapter() {

            }
            item.images?.large?.let { imageAdapter.addList(it) }
            imageRecycler.adapter = imageAdapter
            imageRecycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        }

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        nmap = googleMap
        nmap.setMinZoomPreference(12F)
        setUpMap()
    }

    private fun setUpMap() {
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) &&
            (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED)
        ) {
            if (item.latitude != null && item.longitude != null) {
                val currentLatLong = LatLng(item.latitude!!, item.longitude!!)
                nmap.setMinZoomPreference(2F)
                nmap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 11f))
                nmap.addMarker(
                    MarkerOptions().position(LatLng(item.latitude!!, item.longitude!!))
                        .title(item.name)
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        (activity as MainActivity).binding.navView.visibility = View.VISIBLE
        super.onDestroyView()
    }
}