package com.onix.internship.okucherenko.ui.translator

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.okucherenko.databinding.TranslateItemViewBinding
import com.onix.internship.okucherenko.domain.entity.ResultTranslate

class TranslateAdapter :
    ListAdapter<ResultTranslate, TranslateAdapter.TranslateItemViewHolder>(TranslateDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslateItemViewHolder {
        return TranslateItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TranslateItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TranslateItemViewHolder private constructor(val binding: TranslateItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val word: TextView = binding.textViewWord
        val translate: TextView = binding.textViewTranslate

        fun bind(
            item: ResultTranslate
        ) {
            word.text = item.word
            word.setTextColor(Color.BLUE)
            translate.text = item.translate
        }

        companion object {
            fun from(parent: ViewGroup): TranslateItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    TranslateItemViewBinding.inflate(layoutInflater, parent, false)
                return TranslateItemViewHolder(binding)
            }
        }
    }
}

class TranslateDiffCallback : DiffUtil.ItemCallback<ResultTranslate>() {
    override fun areItemsTheSame(oldItem: ResultTranslate, newItem: ResultTranslate): Boolean {
        return oldItem.word == newItem.word
    }

    override fun areContentsTheSame(oldItem: ResultTranslate, newItem: ResultTranslate): Boolean {
        return oldItem == newItem
    }
}