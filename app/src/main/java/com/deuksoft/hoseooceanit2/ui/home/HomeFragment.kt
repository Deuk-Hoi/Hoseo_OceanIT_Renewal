package com.deuksoft.hoseooceanit2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.ui.MainActivity
import com.deuksoft.hoseooceanit2.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _HomeBinding: FragmentHomeBinding? = null

    private val homeBinding get() = _HomeBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _HomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.getLatelyField().observe(viewLifecycleOwner){
            homeBinding.apply {
                researchName.text = it.detailData[0].research_name_ko
                researchContent.text = it.detailData[0].research_content_ko
                researchContent.isSelected = true
                if(it.photoData.size != 0){
                    Glide.with(requireContext()).load(it)
                        .error(Glide.with(requireContext()).load(R.drawable.ocean_main))
                        .apply(
                            RequestOptions()
                            .signature(ObjectKey(System.currentTimeMillis()))
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                        )
                        .into(researchImg)
                }
            }

        }

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