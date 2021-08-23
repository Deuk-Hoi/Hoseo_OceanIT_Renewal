package com.deuksoft.hoseooceanit2.DialogManager

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.database.DatabaseUtils
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.FieldDTO
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.FieldDialogLayoutBinding
import java.util.*

class FieldDialog(context: Context, var fieldDTO: FieldDTO):Dialog(context) {
    lateinit var fieldDialogLayoutBinding: FieldDialogLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.field_dialog_layout)
        findViewById<TextView>(R.id.fieldDetailTitle).apply {
            text = fieldDTO.research_name_ko
            isSelected = true
        }
        findViewById<TextView>(R.id.departmentTxt).apply {
            text = fieldDTO.department_name_ko
            isSelected = true
        }
        findViewById<TextView>(R.id.businessNameTxt).apply {
            text = fieldDTO.business_name_ko
            isSelected = true
        }
        findViewById<TextView>(R.id.supportNameTxt).apply {
            text = fieldDTO.support_agency_ko
            isSelected = true
        }
        findViewById<TextView>(R.id.researchPeriodTxt).text = "${fieldDTO.date_start} ~ ${fieldDTO.date_end}"
        findViewById<TextView>(R.id.participateNameTxt).apply {
            text = fieldDTO.participation_agency_ko
            isSelected = true
        }
        findViewById<TextView>(R.id.managerNameTxt).text = fieldDTO.research_manager_ko
        findViewById<TextView>(R.id.researchGoalTxt).text = fieldDTO.research_goal_ko
        findViewById<TextView>(R.id.researchContentTxt).text = fieldDTO.research_content_ko
        findViewById<TextView>(R.id.expectedPerformTxt).text = fieldDTO.expectation_result_ko
    }
}