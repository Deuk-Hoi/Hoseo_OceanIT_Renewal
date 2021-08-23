package com.deuksoft.hoseooceanit2.itemAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.FieldDTO
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.FieldItemBinding
import java.text.SimpleDateFormat
import java.util.*

class FieldAdapter (var context: Context, var FieldList: List<FieldDTO>, var itemClick: (FieldDTO) -> Unit): RecyclerView.Adapter<FieldAdapter.FieldViewHolder>() {
    var dateFormat = SimpleDateFormat("yyyy-MM-dd")
    inner class FieldViewHolder(fieldItemBinding: FieldItemBinding, itemClick: (FieldDTO) -> Unit): RecyclerView.ViewHolder(fieldItemBinding.root){

        var fieldTitle = fieldItemBinding.fieldTitle
        var fieldContent = fieldItemBinding.fieldContent
        var researchPeriod = fieldItemBinding.researchPeriod
        var researchState = fieldItemBinding.researchState

        /*
        * 각 분류에 따라 중간에 들어가는 컨텐츠가 다르다 따라서 각 분류에 맞게 커스텀을 해주었다.
        * */
        fun bind(fieldDTO: FieldDTO, context: Context){
            var endDate = dateFormat.parse(fieldDTO.date_end)
            var currendDate = dateFormat.parse(dateFormat.format(Date()))
            fieldTitle.text = fieldDTO.research_name_ko
            fieldContent.text = fieldDTO.research_goal_ko
            researchPeriod.text = "${fieldDTO.date_start} ~ ${fieldDTO.date_end}"
            if(currendDate.compareTo(endDate) < 0){
                researchState.text = "연구 진행중"
                researchState.setTextColor(context.resources.getColor(R.color.resultThesis, null))
            }else{
                researchState.text = "연구 완료"
                researchState.setTextColor(context.resources.getColor(R.color.customBlue, null))
            }
            itemView.setOnClickListener{itemClick(fieldDTO)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldAdapter.FieldViewHolder {
        var fieldItemBinding = FieldItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FieldViewHolder(fieldItemBinding, itemClick)
    }

    override fun onBindViewHolder(holder: FieldAdapter.FieldViewHolder, position: Int) {
        holder.bind(FieldList[position], context)
    }

    override fun getItemCount(): Int {
        return FieldList.size
    }
}