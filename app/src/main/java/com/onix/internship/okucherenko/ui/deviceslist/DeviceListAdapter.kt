package com.onix.internship.okucherenko.ui.deviceslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.okucherenko.data.repository.entity.DeviceItem
import com.onix.internship.okucherenko.databinding.DeviceItemBinding

class DeviceListAdapter(private val deviceItemListener: DeviceItemListener) : ListAdapter<DeviceItem, DeviceListAdapter.ViewHolder>(DeviceListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, deviceItemListener)
    }

    class ViewHolder private constructor(val binding: DeviceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DeviceItem, deviceItemListener: DeviceItemListener) {
            binding.device = item
            binding.executePendingBindings()
            binding.deviceItemListener = deviceItemListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DeviceItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class DeviceListDiffCallback : DiffUtil.ItemCallback<DeviceItem>() {
    override fun areItemsTheSame(oldItem: DeviceItem, newItem: DeviceItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DeviceItem, newItem: DeviceItem): Boolean {
        return oldItem == newItem
    }
}

class DeviceItemListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(device: DeviceItem) = clickListener(device.id)
}


