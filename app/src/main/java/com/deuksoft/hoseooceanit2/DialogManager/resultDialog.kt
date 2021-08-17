package com.deuksoft.hoseooceanit2.DialogManager

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.ResultDTO
import com.deuksoft.hoseooceanit2.databinding.ResultDialogLayoutBinding

class resultDialog(context: Context, resultDTO: ResultDTO): Dialog(context) {
    lateinit var resultDialogBinding: ResultDialogLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultDialogBinding = ResultDialogLayoutBinding.inflate(layoutInflater)

    }
}