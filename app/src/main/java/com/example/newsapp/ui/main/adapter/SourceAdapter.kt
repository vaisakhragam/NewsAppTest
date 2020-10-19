package com.example.Sourceapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.model.Source

import kotlinx.android.synthetic.main.item_sources_layout.view.*

class SourceAdapter(
    private val sources: ArrayList<Source>
) : RecyclerView.Adapter<SourceAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(source: Source) {
            itemView.textView_source.text = source.name
            itemView.textView_description.text = source.description
            itemView.textView_category.text = source.category
            itemView.textView_language.text = source.language
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sources_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = sources.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(sources[position])

    fun addData(list: List<Source>) {
        sources.clear()
        sources.addAll(list)
    }


     fun String.trimDescription():String
    {


        return take(15)
    }
}