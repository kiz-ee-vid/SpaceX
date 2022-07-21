package com.example.spacex.presentation.rocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex.data.RepositoryImpl
import javax.inject.Inject

class RocketViewModel @Inject constructor(private val repo: RepositoryImpl): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}