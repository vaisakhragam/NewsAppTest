package com.example.newsapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.model.News


class MainAdapter(
    private val news: ArrayList<News>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            /*itemView.textViewUserName.text = news.author
            itemView.textViewUserEmail.text = news.publishedAt
            Glide.with(itemView.imageViewAvatar.context)
                .load(news.urlToImage)
                .into(itemView.imageViewAvatar)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sources_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(news[position])

    fun addData(list: List<News>) {
        news.addAll(list)
    }
}