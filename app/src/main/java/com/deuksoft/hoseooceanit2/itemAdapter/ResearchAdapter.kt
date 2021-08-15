package com.deuksoft.hoseooceanit2.itemAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.ResultDTO
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.ResultItemBinding

class ResearchAdapter(var context: Context, var resultList: List<ResultDTO>, var itemClick: (ResultDTO) -> Unit):RecyclerView.Adapter<ResearchAdapter.ResearchViewHolder>() {
    inner class ResearchViewHolder(resultItemBinding: ResultItemBinding, itemClick: (ResultDTO) -> Unit):RecyclerView.ViewHolder(resultItemBinding.root){

        var resultTitle = resultItemBinding.resultTitle
        var resultContent = resultItemBinding.resultContent
        var announeDate = resultItemBinding.announeDate
        var writer = resultItemBinding.writer
        var classify = resultItemBinding.classify

        /*
        * 각 분류에 따라 중간에 들어가는 컨텐츠가 다르다 따라서 각 분류에 맞게 커스텀을 해주었다.
        * */
        fun bind(resultDTO: ResultDTO, context: Context){
            resultTitle.text = resultDTO.title_ko
            if(resultDTO.classify_ko == "논문"){
                resultContent.text = resultDTO.media_ko
                classify.setTextColor(context.resources.getColor(R.color.resultThesis, null))
            }else if(resultDTO.classify_ko == "발표"){
                resultContent.text = resultDTO.academic_ko
                classify.setTextColor(context.resources.getColor(R.color.resultAnnounced, null))
            }else if(resultDTO.classify_ko == "특허"){
                resultContent.text = resultDTO.application_num
                classify.setTextColor(context.resources.getColor(R.color.resultPatent, null))
            }
            announeDate.text = resultDTO.date
            writer.text = resultDTO.writer_ko
            classify.text = "[ ${resultDTO.classify_ko} ]"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchAdapter.ResearchViewHolder {
        var resultItemBinding = ResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResearchViewHolder(resultItemBinding, itemClick)
    }

    override fun onBindViewHolder(holder: ResearchAdapter.ResearchViewHolder, position: Int) {
        holder.bind(resultList[position], context)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }
}