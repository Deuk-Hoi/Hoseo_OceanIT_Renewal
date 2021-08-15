package com.deuksoft.hoseooceanit2.ui.field

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.FieldDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager.ResearchRepository

class FieldViewModel(application: Application): AndroidViewModel(application) {
    var researchRepository = ResearchRepository()

    fun getField(classify: String):LiveData<List<FieldDTO>>{
        return researchRepository.getField(classify)
    }
    fun getMessage():LiveData<String>{
        return researchRepository.message
    }
}