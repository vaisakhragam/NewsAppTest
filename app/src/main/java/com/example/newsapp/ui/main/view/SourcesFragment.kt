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
import com.example.Sourceapp.ui.main.adapter.SourceAdapter
import com.example.newsapp.R
import com.example.newsapp.data.model.Source
import com.example.newsapp.ui.main.viewmodel.SourcesViewModel
import com.example.newsapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sources.*

@AndroidEntryPoint
class SourcesFragment : Fragment() {

    private val sourcesViewModel: SourcesViewModel by viewModels()
    private lateinit var sourceAdapter: SourceAdapter
    private var sourcesView: View? = null
    var mContainerId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sourcesView = inflater.inflate(R.layout.fragment_sources, container, false)
        mContainerId = container?.id ?: -1
        return sourcesView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {

        sourceAdapter = SourceAdapter(arrayListOf())
        recyclerView_sources.apply {

            layoutManager = LinearLayoutManager(activity)


            val dividerItemDecoration = DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            )
            dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider))


            addItemDecoration(


                dividerItemDecoration
            )

            adapter = sourceAdapter
        }


    }

    private fun setupObserver() {
        sourcesViewModel.sources.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar_sources.visibility = View.GONE
                    it.data?.let {
                        renderList(it)
                    }
                    recyclerView_sources.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar_sources.visibility = View.VISIBLE
                    progressBar_sources.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar_sources.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(sources: List<Source>) {
        sourceAdapter.addData(sources)
        sourceAdapter.notifyDataSetChanged()
    }

}