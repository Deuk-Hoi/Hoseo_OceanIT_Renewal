package com.deuksoft.hoseooceanit2.ui.field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.deuksoft.hoseooceanit2.DialogManager.FieldDialog
import com.deuksoft.hoseooceanit2.ui.MainActivity
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.FragmentFieldBinding
import com.deuksoft.hoseooceanit2.itemAdapter.FieldAdapter

class FieldFragment: Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var fieldViewModel: FieldViewModel
    private var _fieldBinding: FragmentFieldBinding? = null
    private val fieldBinding get() = _fieldBinding!!
    lateinit var spinner: Spinner
    var researchState = hashMapOf(
        "전체 과제" to "all",
        "진행 과제" to "progress",
        "완료 과제" to "finish"
    )
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        fieldViewModel = ViewModelProvider(this).get(FieldViewModel::class.java)
        _fieldBinding = FragmentFieldBinding.inflate(inflater, container, false)
        requireActivity().findViewById<TextView>(R.id.contentTitle).text = "연구 과제"
        spinner = requireActivity().findViewById(R.id.contentList)!!
        settingSpinner()

        fieldBinding.apply {
            fieldViewModel = fieldViewModel
            lifecycleOwner = this@FieldFragment
        }

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
        getField(researchState!!.get(spinner.getItemAtPosition(position).toString())!!)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun getField(classify: String){
        fieldViewModel.getField(classify).observe(viewLifecycleOwner){
            var fieldAdapter = FieldAdapter(requireContext(), it){
                FieldDialog(requireContext(), it).show()
            }
            val linearManager = LinearLayoutManager(requireContext())
            fieldBinding.fieldRecycler.adapter = fieldAdapter
            fieldBinding.fieldRecycler.layoutManager = linearManager
            fieldBinding.fieldRecycler.setHasFixedSize(true)
            fieldAdapter.notifyDataSetChanged()
        }
    }
}