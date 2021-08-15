package com.deuksoft.hoseooceanit2.ui.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.ResultDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager.ResearchRepository

class ResultViewModel(application: Application) : AndroidViewModel(application){
    var researchRepository = ResearchRepository()

    fun getResearch(classify : String):LiveData<List<ResultDTO>>{
        return researchRepository.getResearch(classify)
    }
    fun getMessage():LiveData<String>{
        return researchRepository.message
    }
}