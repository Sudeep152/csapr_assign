package com.shashank.cspar_project.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shashank.cspar_project.repository.repo
import com.shashank.cspar_project.viewmodel.Mainviewmodel

class MainViewModelFactory(val repository: repo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return  Mainviewmodel(repository) as T
    }
}