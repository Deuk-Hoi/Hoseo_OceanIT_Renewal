package com.deuksoft.hoseooceanit2.ui.member

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.deuksoft.hoseooceanit2.MainActivity
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.AppBarMainBinding
import com.deuksoft.hoseooceanit2.databinding.FragmentMemberBinding
import com.deuksoft.hoseooceanit2.itemAdapter.MemberAdapter

class MemberFragment : Fragment() {

    private lateinit var memberViewModel: MemberViewModel
    private var _memberBinding: FragmentMemberBinding? = null
    private val memberBinding get() = _memberBinding!!

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        memberViewModel = ViewModelProvider(this).get(MemberViewModel::class.java)
        _memberBinding = FragmentMemberBinding.inflate(inflater, container, false)
        requireActivity().findViewById<TextView>(R.id.contentTitle).text = "연구 인력"

        memberBinding.apply {
            memberViewModel = memberViewModel
            memberBinding.lifecycleOwner = this@MemberFragment
        }


        //연구인력 리스트 불러오는 작업
        memberViewModel.getMember().observe(viewLifecycleOwner){
            val memberAdapter = MemberAdapter(requireContext(), it){
                //유저별 상세정보를 띄우려면 여기서 하면됨
            }
            val linearManager = LinearLayoutManager(requireContext())
            memberBinding.memberRecycler.adapter = memberAdapter
            memberBinding.memberRecycler.layoutManager = linearManager
            memberBinding.memberRecycler.setHasFixedSize(true)
            memberAdapter.notifyDataSetChanged()
        }

        return memberBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).otherContentAppbar(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _memberBinding = null
    }
}