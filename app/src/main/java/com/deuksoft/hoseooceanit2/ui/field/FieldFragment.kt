package com.deuksoft.hoseooceanit2.ui.field

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.deuksoft.hoseooceanit2.MainActivity
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.FragmentFieldBinding

class FieldFragment: Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var fieldViewModel: FieldViewModel
    private var _fieldBinding: FragmentFieldBinding? = null
    private val fieldBinding get() = _fieldBinding!!
    lateinit var spinner: Spinner

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        fieldViewModel = ViewModelProvider(this).get(FieldViewModel::class.java)
        _fieldBinding = FragmentFieldBinding.inflate(inflater, container, false)
        spinner = requireActivity().findViewById(R.id.contentList)!!
        settingSpinner()
        spinner.onItemSelectedListener = this
        return fieldBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).otherContentAppbar(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fieldBinding = null
    }

    /*
    * 색을 커스텀한 스피너를 어뎁터에 연결해주는 역할을 한다.
    * */
    fun settingSpinner(){
        var adapter = ArrayAdapter.createFromResource(requireContext(), R.array.fieldList,R.layout.color_spinner_layout)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        spinner.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.e("sdfdsf", spinner.getItemAtPosition(position).toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}