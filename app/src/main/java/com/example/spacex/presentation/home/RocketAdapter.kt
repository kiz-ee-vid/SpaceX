package com.example.spacex.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacex.data.model.Rocket
import com.example.spacex.databinding.ItemRocketBinding

class RocketAdapter(val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<RocketAdapter.RocketHolder>() {

    private var rocketList = ArrayList<Rocket>()

    inner class RocketHolder(val binding: ItemRocketBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketHolder {
        val binding = ItemRocketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RocketHolder(binding)
    }

    override fun onBindViewHolder(holder: RocketHolder, position: Int) {
        with(holder.binding){
            Glide.with(rocketImage)
                .load(rocketList[position].flickr_images[0])
                .into(rocketImage)
            rocketTittle.text = rocketList[position].name
            rocketLaunch.text = rocketList[position].first_flight
            rocketCost.text = rocketList[position].cost_per_launch.toString()
            rocketSuccess.text = rocketList[position].success_rate_pct.toString()
        }

        holder.itemView.setOnClickListener {
            itemClick(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(rockets: ArrayList<Rocket>){
        rocketList.clear()
        rocketList.addAll(rockets)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return rocketList.size
    }
}