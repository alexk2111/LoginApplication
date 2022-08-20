package com.onix.internship.okucherenko.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.okucherenko.data.Recording
import com.onix.internship.okucherenko.databinding.RecordingItemBinding

class SongItemAdapter(private val songItemListener: RecordingListener) : ListAdapter<Recording, SongItemAdapter.ViewHolder>(RecordingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, songItemListener)
    }

    class ViewHolder private constructor(val binding: RecordingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recording, deviceItemListener: RecordingListener) {
            binding.recording = item
            binding.executePendingBindings()
            binding.recordingItemListener = deviceItemListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecordingItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class RecordingDiffCallback : DiffUtil.ItemCallback<Recording>() {
    override fun areItemsTheSame(oldItem: Recording, newItem: Recording): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recording, newItem: Recording): Boolean {
        return oldItem == newItem
    }
}

class RecordingListener(val clickListener: (item: Recording) -> Unit) {
    fun onClick(recording: Recording) = clickListener(recording)
}
