package com.deuksoft.hoseooceanit2.ui.member

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.MemberDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager.MemberRepository

class MemberViewModel(application: Application) : AndroidViewModel(application){
    var memberRepository = MemberRepository()

    fun getMember():LiveData<List<MemberDTO>>{
        return memberRepository.getMember()
    }
}