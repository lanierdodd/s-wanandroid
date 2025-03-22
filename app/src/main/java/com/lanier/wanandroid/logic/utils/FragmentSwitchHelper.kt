package com.lanier.wanandroid.logic.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class FragmentSwitchHelper(
    private val resId: Int,
    private val fragmentManager: FragmentManager
) {

    var showIndex = -1
        private set

    val fragmentSize: Int
        get() = _fragments.size

    private val _fragments = mutableListOf<SwitchFragment>()

    fun setFragments(
        fragments: List<SwitchFragment>,
    ) {
        _fragments.clear()
        _fragments.addAll(fragments)
    }

    fun addFragment(
        fragment: Fragment,
        tag: String = fragment::class.java.simpleName
    ) {
        _fragments.add(
            SwitchFragment(
                fragment = fragment,
                tag = tag
            )
        )
    }

    fun switchFirstFraSafely() {
        if (_fragments.size > 0) {
            switchFra(0)
        }
    }

    fun switchFra(index: Int) {
        if (_fragments.isEmpty()) {
            return
        }
        if (index < 0 || index > _fragments.size - 1) {
            return
        }
        fragmentManager.commit {
            if (showIndex != index) {
                if (showIndex != -1) {
                    hide(getFraByIndex(showIndex))
                }
                val needShowFragment = getFraByIndex(index)
                if (needShowFragment.isAdded) {
                    show(needShowFragment)
                } else {
                    val tag = getTagByIndex(index)
                    add(resId, needShowFragment, tag)
                    show(needShowFragment)
                }
                showIndex = index
            }
        }
    }

    private fun getFraByIndex(index: Int) = _fragments[index].fragment

    private fun getTagByIndex(index: Int) = _fragments[index].tag

    data class SwitchFragment(
        val fragment: Fragment,
        val tag: String = fragment.javaClass.simpleName
    )
}