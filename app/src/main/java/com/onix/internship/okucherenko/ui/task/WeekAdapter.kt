package com.onix.internship.okucherenko.ui.task

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.data.repository.entity.ActualDate
import com.onix.internship.okucherenko.data.repository.entity.MyWeekDate
import com.onix.internship.okucherenko.databinding.DayofweekItemBinding

class WeekAdapter(val clickListener: WeekListener) : RecyclerView.Adapter<WeekAdapter.ViewHolder>() {

    var data = listOf<MyWeekDate>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]
        holder.bind(item, clickListener)
    }

    override fun getItemCount() = data.size

    class ViewHolder private constructor(val binding: DayofweekItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyWeekDate, clickListener: WeekListener) {
            binding.itemDate = item
            if (item.weekDay == ActualDate.actualDate) {
                binding.linearLayoutDayOfWeek.setBackgroundColor(itemView.resources.getColor(R.color.color_background_item, null))
                binding.textViewDayName.setTextColor(Color.WHITE)
                binding.textViewDayValue.setTextColor(Color.WHITE)
            } else {
                binding.linearLayoutDayOfWeek.setBackgroundColor(Color.WHITE)
                binding.textViewDayName.setTextColor(itemView.resources.getColor(R.color.color_text_month, null))
                binding.textViewDayValue.setTextColor(itemView.resources.getColor(R.color.color_text_month, null))
            }
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DayofweekItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}

class WeekListener(val clickListener: (itemDate: MyWeekDate) -> Unit) {
    fun onClick(myWeekDate: MyWeekDate) = clickListener(myWeekDate)
}

