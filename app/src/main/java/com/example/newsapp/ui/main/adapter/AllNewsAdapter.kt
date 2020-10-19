package com.example.Sourceapp.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.model.News
import com.example.newsapp.ui.main.view.NewsDetailActivity
import kotlinx.android.synthetic.main.item_allnews_layout.view.*

class AllNewsAdapter(
    private val sources: ArrayList<News>
) : RecyclerView.Adapter<AllNewsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(allNews: News) {
            itemView.textView_title.text = allNews.title
            itemView.textView_description.text = allNews.description
            itemView.textView_author.text = allNews.author
            itemView.textView_publishedAt.text = allNews.publishedAt
            itemView.textView_url.text = allNews.url

            Glide.with(itemView.imageView_url.context)
                .load(allNews.urlToImage)
                .into(itemView.imageView_url)

        itemView.setOnClickListener {

            val intent =Intent(it.context,NewsDetailActivity::class.java)
            intent.putExtra("TITLE",allNews.title)
            it.context.startActivity(intent)
        }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_allnews_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = sources.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(sources[position])

    fun addData(list: List<News>) {
        sources.clear()
        sources.addAll(list)
    }


     fun String.trimDescription():String
    {


        return take(15)
    }
}