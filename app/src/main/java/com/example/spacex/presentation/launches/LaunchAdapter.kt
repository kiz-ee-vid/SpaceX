package com.example.spacex.presentation.launches

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
import com.example.spacex.databinding.ItemRocketBinding
import com.example.spacex.domain.ui_model.UiLaunch

class LaunchAdapter(val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<LaunchAdapter.LaunchHolder>() {

    private var launchesList = ArrayList<UiLaunch>()

    inner class LaunchHolder(val binding: ItemLaunchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchHolder {
        val binding = ItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchHolder, position: Int) {
        with(holder.binding){
            Glide.with(launchImage)
                .load(launchesList[position].links?.patch?.small)
                .into(launchImage)
            launchTitle.text = launchesList[position].name
            launchDate.text = launchesList[position].dateUtc?.substring(0, 10) ?: "No data"
            if (launchesList[position].upcoming == false){
                upcoming.setImageResource(R.drawable.ic_completed)
            }
            else upcoming.setImageResource(R.drawable.ic_upcoming)
            number.text = "#".plus(launchesList[position].flightNumber)
        }


        holder.itemView.setOnClickListener {
            itemClick(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(launches: ArrayList<UiLaunch>){
        launchesList.clear()
        launchesList.addAll(launches)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return launchesList.size
    }
}