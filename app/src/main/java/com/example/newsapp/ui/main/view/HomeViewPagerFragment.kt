/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.newsapp.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.ui.main.adapter.NewsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_view_pager.*

const val SOURCES_PAGE_INDEX = 0
const val ALL_NEWS_PAGE_INDEX = 1
@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {


    private var sourcesView : View? = null
    var mContainerId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sourcesView = inflater.inflate(R.layout.fragment_view_pager, container, false)
        mContainerId = container?.id?:-1




        return  sourcesView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      view_pager.adapter = NewsPagerAdapter(this)


        // Set the text for each tab
        TabLayoutMediator(tabs, view_pager) { tab, position ->

            tab.text = getTabTitle(position)
        }.attach()




    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
             SOURCES_PAGE_INDEX-> "sources"
            ALL_NEWS_PAGE_INDEX -> "all news"
            else -> null
        }
    }
}
