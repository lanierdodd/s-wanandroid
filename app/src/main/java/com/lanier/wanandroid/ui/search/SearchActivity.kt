package com.lanier.wanandroid.ui.search

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lanier.wanandroid.R
import com.lanier.wanandroid.ui.main.HomeDataAdapter
import com.lanier.wanandroid.ui.main.HomeViewModel
import kotlin.getValue

class SearchActivity : AppCompatActivity() {

    private val viewmodel by viewModels<SearchViewModel>()
    private val mAdapter = HomeDataAdapter()

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                (recyclerView.layoutManager as? LinearLayoutManager)?.let { lm ->
                    val totalItemCount = lm.itemCount
                    val lastVisibleItem = lm.findLastVisibleItemPosition()

                    if (totalItemCount <= lastVisibleItem + 1) {
                        search(false)
                    }
                }
            }
        })

        viewmodel.homeDataLiveData.observe(this) {
            if (viewmodel.isRefresh) {
                mAdapter.data = it
            } else {
                mAdapter.addData(it)
            }
        }

        btnSearch.setOnClickListener {
            search(true)
        }
    }

    private fun search(refresh: Boolean) {
        val keyword = etSearch.text.toString()
        viewmodel.search(keyword = keyword, refresh)
    }
}