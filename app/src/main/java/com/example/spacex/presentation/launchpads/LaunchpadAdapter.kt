package com.example.spacex.presentation.launchpads

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacex.R
import com.example.spacex.data.model.Launch
import com.example.spacex.data.model.Rocket
import com.example.spacex.databinding.ItemLaunchBinding
import com.example.spacex.databinding.ItemLaunchpadBinding
import com.example.spacex.databinding.ItemRocketBinding
import com.example.spacex.domain.ui_model.UiLaunch
import com.example.spacex.domain.ui_model.UiLaunchpad

class LaunchpadAdapter(val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<LaunchpadAdapter.LaunchpadHolder>() {

    private var launchpadsList = ArrayList<UiLaunchpad>()

    inner class LaunchpadHolder(val binding: ItemLaunchpadBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchpadHolder {
        val binding = ItemLaunchpadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchpadHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchpadHolder, position: Int) {
        with(holder.binding){
            launchpadTitle.text = launchpadsList[position].name
            launchpadCity.text = launchpadsList[position].region
            launchpadStatus.text = launchpadsList[position].status
        }

        holder.itemView.setOnClickListener {
            itemClick(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(launchpads: ArrayList<UiLaunchpad>){
        launchpadsList.clear()
        launchpadsList.addAll(launchpads)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return launchpadsList.size
    }
}