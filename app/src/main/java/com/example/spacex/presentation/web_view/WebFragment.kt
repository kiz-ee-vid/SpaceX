package com.example.spacex.presentation.web_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.spacex.R
import com.example.spacex.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        val args : WebFragmentArgs by navArgs()
        val address = args.address

        binding.webBrowser.loadUrl(address)
        binding.webBrowser.settings.javaScriptEnabled = true
        binding.webBrowser.webViewClient = WebViewClient()

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println(item.itemId)
        when (item.itemId) {
            android.R.id.home -> {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_navigation_web_to_navigation_rocket)
            }
        }
        return true
    }
}