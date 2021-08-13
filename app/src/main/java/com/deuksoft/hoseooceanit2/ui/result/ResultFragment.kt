package com.deuksoft.hoseooceanit2.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.deuksoft.hoseooceanit2.MainActivity
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var resultViewModel: ResultViewModel
    private var _resultBinding: FragmentResultBinding? = null

    private val resultBinding get() = _resultBinding!!
    lateinit var spinner: Spinner
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        resultViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        _resultBinding = FragmentResultBinding.inflate(inflater, container, false)
        spinner = requireActivity().findViewById(R.id.contentList)!!
        settingSpinner()
        return resultBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).otherContentAppbar(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _resultBinding = null
    }

    /*
    * 색을 커스텀한 스피너를 어뎁터에 연결해주는 역할을 한다.
    * */
    fun settingSpinner(){
        var adapter = ArrayAdapter.createFromResource(requireContext(), R.array.resultList, R.layout.color_spinner_layout)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        spinner.adapter = adapter
    }
}