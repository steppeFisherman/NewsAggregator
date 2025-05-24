package com.example.newsaggregator.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.databinding.NewsItemRawBinding
import com.example.newsaggregator.ui.model.DataUi
import com.example.newsaggregator.utils.LoadImage

typealias Listener = (dataUi: DataUi) -> Unit

class MainFragmentAdapter(private val loadImage: LoadImage, private val listener: Listener) :
    ListAdapter<DataUi, MainFragmentAdapter.MainHolder>(ItemCallback), View.OnClickListener {

    override fun onClick(v: View) {
        val dataUi = v.tag as DataUi
        when (v.id) {
            R.id.container -> listener(dataUi)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = NewsItemRawBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        view.container.setOnClickListener(this)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            root.tag = item
            container.tag = item

            loadImage.load(holder.itemView.context, newsImage, item.image)
            title.text = item.title
            description.text = item.description
            dcDate.text = item.dcDate
            author.text = item.dcCreator
        }
    }

    class MainHolder(val binding: NewsItemRawBinding) : RecyclerView.ViewHolder(binding.root)
}