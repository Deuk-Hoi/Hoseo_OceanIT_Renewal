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

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _HomeBinding: FragmentHomeBinding? = null

    private val homeBinding get() = _HomeBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _HomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        homeBinding.memberBtn.setOnClickListener(this)
        homeBinding.researchBtn.setOnClickListener(this)
        homeBinding.fieldBtn.setOnClickListener(this)
        homeBinding.waterBtn.setOnClickListener(this)

        return homeBinding.root
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

    override fun onClick(v: View?) {
        when(v?.id){
            homeBinding.memberBtn.id->{
                (activity as MainActivity).changeFragment(0)
            }
            homeBinding.researchBtn.id->{
                (activity as MainActivity).changeFragment(1)
            }
            homeBinding.fieldBtn.id->{
                (activity as MainActivity).changeFragment(2)
            }
            homeBinding.waterBtn.id->{
                (activity as MainActivity).changeFragment(3)
            }
        }
    }
}