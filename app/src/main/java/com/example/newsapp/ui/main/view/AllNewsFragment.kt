package com.example.newsapp.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Sourceapp.ui.main.adapter.AllNewsAdapter
import com.example.newsapp.R
import com.example.newsapp.data.model.News
import com.example.newsapp.ui.main.viewmodel.AllNewsViewModel
import com.example.newsapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_news.*



@AndroidEntryPoint
class AllNewsFragment : Fragment() {

    private val allNewsViewModel: AllNewsViewModel by viewModels()
    private lateinit var allNewsAdapter: AllNewsAdapter
    private var allNewsView: View? = null
    var mContainerId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allNewsView = inflater.inflate(R.layout.fragment_all_news, container, false)
        mContainerId = container?.id ?: -1
        return allNewsView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {

        allNewsAdapter = AllNewsAdapter(arrayListOf())
        recyclerView_allNews.apply {

            layoutManager = LinearLayoutManager(activity)


            val dividerItemDecoration = DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            )
            dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider))


            addItemDecoration(


                dividerItemDecoration
            )

            adapter = allNewsAdapter
        }


    }

    private fun setupObserver() {
        allNewsViewModel.allNews.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar_allNews.visibility = View.GONE
                    it.data?.let {
                        renderList(it)
                    }
                    recyclerView_allNews.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar_allNews.visibility = View.VISIBLE
                    progressBar_allNews.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar_allNews.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(allNews: List<News>) {
        allNewsAdapter.addData(allNews)
        allNewsAdapter.notifyDataSetChanged()
    }

}