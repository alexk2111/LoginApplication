package com.onix.internship.okucherenko.ui.points

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.okucherenko.data.repository.entity.Point
import com.onix.internship.okucherenko.databinding.PointItemViewBinding
import java.util.*

class PointAdapter: RecyclerView.Adapter<PointAdapter.ViewHolder>() {

    var data =  listOf<Point>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    override fun getItemCount() = data.size

    class ViewHolder private constructor(val binding: PointItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: Point
        ) {
            item.date = Calendar.getInstance().time

            binding.point = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PointItemViewBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }

    }
}

