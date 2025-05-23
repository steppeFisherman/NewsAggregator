package com.example.newsaggregator.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.newsaggregator.ui.model.DataUi

object ItemCallback : DiffUtil.ItemCallback<DataUi>() {
    override fun areItemsTheSame(oldItem: DataUi, newItem: DataUi) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: DataUi, newItem: DataUi) = oldItem == newItem
}