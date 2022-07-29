package com.example.spacex.presentation.rockets.rocket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacex.databinding.ItemImageBinding

class ImageAdapter(val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    private var imageList = ArrayList<String>()

    inner class ImageHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        with(holder.binding){
            Glide.with(itemImage)
                .load(imageList[position])
                .into(itemImage)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(image: ArrayList<String>){
        imageList.clear()
        imageList.addAll(image)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}