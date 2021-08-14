package com.deuksoft.hoseooceanit2.itemAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.MemberDTO
import com.deuksoft.hoseooceanit2.HTTPManager.Tools
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.databinding.MemberItemBinding

class MemberAdapter(var context: Context, var memberList: List<MemberDTO>, var itemClick: (MemberDTO) -> Unit):RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    inner class MemberViewHolder(memberItemBinding: MemberItemBinding, itemClick: (MemberDTO) -> Unit):RecyclerView.ViewHolder(memberItemBinding.root){
        var userName = memberItemBinding.userName
        var userPosition = memberItemBinding.userPosition
        var department = memberItemBinding.departement
        var phoneNum = memberItemBinding.phoneNum
        var email = memberItemBinding.email
        var userImg = memberItemBinding.userImg

        fun bind(memberDTO: MemberDTO, context: Context){
            userName.text = memberDTO.name_ko
            userPosition.text = memberDTO.position_ko
            department.text = "호서대학교 ${memberDTO.department_ko}"
            phoneNum.text = memberDTO.phone
            email.text = memberDTO.email
            var imagURL = "${Tools().IMAGE_LOAD_URL}${memberDTO.photo}"
            //Glide 이미지 캐시 지우는 기능 추가
            Glide.with(context).load(imagURL)
                .error(Glide.with(context).load(R.drawable.no_image))
                .apply(
                    RequestOptions()
                        .signature(ObjectKey(System.currentTimeMillis()))
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                ).into(userImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdapter.MemberViewHolder {
        var memberItemBinding = MemberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(memberItemBinding, itemClick)
    }

    override fun onBindViewHolder(holder: MemberAdapter.MemberViewHolder, position: Int) {
        holder.bind(memberList[position], context)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }
}