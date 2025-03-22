package com.lanier.wanandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lanier.wanandroid.R
import com.lanier.wanandroid.logic.model.HomeRealDataX
import com.lanier.wanandroid.logic.utils.TimeUtils

class HomeDataAdapter : RecyclerView.Adapter<HomeDataHolder>() {

    private val _list = mutableListOf<HomeRealDataX>()
    var data: List<HomeRealDataX>
        set(value) {
            _list.clear()
            _list.addAll(value)
            notifyDataSetChanged()
        }
        get() = _list

    fun addData(list: List<HomeRealDataX>) {
        val curIndex = _list.size - 1
        _list.addAll(list)
        notifyItemRangeInserted(curIndex, list.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeDataHolder {
        return HomeDataHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: HomeDataHolder,
        position: Int
    ) {
        val homeData = _list[position]
        var sharedUsername = homeData.shareUser
        if (sharedUsername.isEmpty()) {
            sharedUsername = homeData.author
        }
        if (sharedUsername.isEmpty()) {
            sharedUsername = "暂无"
        }
        holder.tvName.text = homeData.title
        holder.tvAuthor.text = "分享人：$sharedUsername"
        holder.tvChapter.text = "分类：${homeData.chapterName}"
        holder.tvTime.text = TimeUtils.convertTimestamp(homeData.shareDate)
    }

    override fun getItemCount(): Int {
        return _list.size
    }
}

class HomeDataHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    val tvName = view.findViewById<TextView>(R.id.tvTitle)
    val tvAuthor = view.findViewById<TextView>(R.id.tvAuthor)
    val tvChapter = view.findViewById<TextView>(R.id.tvChapter)
    val tvTime = view.findViewById<TextView>(R.id.tvTime)
}