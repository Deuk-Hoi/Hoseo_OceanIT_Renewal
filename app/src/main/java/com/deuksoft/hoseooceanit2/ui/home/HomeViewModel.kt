package com.deuksoft.hoseooceanit2.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.LatelyFieldDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager.MainRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {
    var mainRepository = MainRepository()

    fun getLatelyField():LiveData<LatelyFieldDTO>{
        return mainRepository.getLatelyField()
    }

}