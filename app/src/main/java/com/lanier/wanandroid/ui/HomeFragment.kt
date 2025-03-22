package com.lanier.wanandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lanier.wanandroid.R

class HomeFragment : Fragment() {

    private val viewmodel by viewModels<HomeViewModel>()
    private val mAdapter = HomeDataAdapter()

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etSearch = view.findViewById(R.id.etSearch)
        btnSearch = view.findViewById(R.id.btnSearch)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                (recyclerView.layoutManager as? LinearLayoutManager)?.let { lm ->
                    val totalItemCount = lm.itemCount
                    val lastVisibleItem = lm.findLastVisibleItemPosition()

                    if (totalItemCount <= lastVisibleItem + 1) {
                        viewmodel.showData()
                    }
                }
            }
        })

        btnSearch.setOnClickListener {
        }

        viewmodel.homeDataLiveData.observe(viewLifecycleOwner) {
            if (viewmodel.isRefresh) {
                mAdapter.data = it
            } else {
                mAdapter.addData(it)
            }
        }

        viewmodel.showData(true)
    }
}