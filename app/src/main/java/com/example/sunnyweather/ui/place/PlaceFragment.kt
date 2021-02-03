package com.example.sunnyweather.ui.place

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunnyweather.databinding.PlaceFragmentBinding
import com.example.sunnyweather.util.showToast

class PlaceFragment : Fragment() {

    companion object {
        fun newInstance() = PlaceFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(PlaceViewModel::class.java)
//        ViewModelProviders.of(this).get(PlaceViewModel::class.java)
    }

    private lateinit var mBinding: PlaceFragmentBinding

    private lateinit var adapter: PlaceAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 初始化
        mBinding = PlaceFragmentBinding.inflate(inflater, container, false)
        // 返回布局
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        mBinding.recyclerView.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        mBinding.recyclerView.adapter = adapter
        mBinding.searchPlaceEdit.addTextChangedListener { text: Editable? ->
            val content = text.toString()
            if (content.isEmpty()) {
                mBinding.recyclerView.visibility = View.GONE
                mBinding.bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            } else {
                viewModel.searchPlaces(content)
            }
        }
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val places = result.getOrNull()
            if (places == null) {
                "未能查询到任何地点".showToast()
                result.exceptionOrNull()?.printStackTrace()
                return@Observer
            }
            mBinding.recyclerView.visibility = View.VISIBLE
            mBinding.bgImageView.visibility = View.GONE
            viewModel.placeList.clear()
            viewModel.placeList.addAll(places)
            adapter.notifyDataSetChanged()
        })
    }

}