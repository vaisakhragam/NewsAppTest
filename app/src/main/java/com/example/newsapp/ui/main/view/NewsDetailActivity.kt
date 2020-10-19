package com.example.newsapp.ui.main.view


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.local.db.entities.AllNewsEntity
import com.example.newsapp.ui.main.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_detail.*

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
private lateinit var title:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

title = intent.getStringExtra("TITLE")

println("TITLE=$title")
        setContentView(R.layout.fragment_news_detail)
setupObserver()

    }


    private fun setupObserver() {

        detailViewModel.setTitle(title)
        detailViewModel.allNews.observe(this, Observer {
            setData(it)
        })
    }

    private fun setData(news: AllNewsEntity) {


        Glide.with(imageView_url)
            .load(news.urlToImage)
            .into(imageView_url)

        textView_title.text = news.title
        textView_content.text = news.content
    }




}
