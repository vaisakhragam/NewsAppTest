package com.example.newsapp.ui.main.view



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /* private val sourcesViewModel : SourcesViewModel by viewModels()
    private lateinit var adapter: SourceAdapter*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setupUI()
        //setupObserver()

    }

    /* private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SourceAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        sourcesViewModel.sources.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let {
                        renderList(it)
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }*/

/*    private fun renderList(sources: List<Source>) {
        adapter.addData(sources)
        adapter.notifyDataSetChanged()
    }*/

}
