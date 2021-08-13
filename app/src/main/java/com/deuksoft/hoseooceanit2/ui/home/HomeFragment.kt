package com.deuksoft.hoseooceanit2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deuksoft.hoseooceanit2.MainActivity
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _HomeBinding: FragmentHomeBinding? = null

    private val HomeBinding get() = _HomeBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _HomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        return HomeBinding.root
    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).mainAppbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("sdfds", "dsfds")
        _HomeBinding = null
    }
}