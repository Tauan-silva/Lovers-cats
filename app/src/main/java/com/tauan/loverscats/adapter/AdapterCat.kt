package com.tauan.loverscats.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.tauan.loverscats.data.response.Data
import com.tauan.loverscats.databinding.ItemCatLayoutBinding

class AdapterCat : RecyclerView.Adapter<AdapterCat.MyViewHolder>() {

    inner class MyViewHolder(val itemCatLayoutBinding: ItemCatLayoutBinding) :
        RecyclerView.ViewHolder(itemCatLayoutBinding.root)

    private val diffCallback =
        object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.images == newItem.images
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCatLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentList = differ.currentList[position]
        val data = currentList.images

        if (data != null) {

            for (image in data) {
                if (image.link.endsWith(".jpg")) {
                    Log.d("ccc", "link ${image.link}")
                    holder.itemCatLayoutBinding.apply {
                        imgCat.load(image.link) {
                            crossfade(true)
                            crossfade(1000)
                            transformations(
                                RoundedCornersTransformation(50f)
                            )
                        }
                    }
                }
            }
        }


    }

    override fun getItemCount(): Int = differ.currentList.size

}