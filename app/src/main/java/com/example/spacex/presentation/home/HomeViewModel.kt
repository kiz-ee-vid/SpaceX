package com.example.spacex.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex.data.RepositoryImpl
import com.example.spacex.data.model.Rocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: RepositoryImpl): ViewModel() {

    val allRockets = MutableLiveData<ArrayList<Rocket>>()

    init {
        println("ILLU")
        CoroutineScope(Dispatchers.IO).launch() {
            try{
                println("Hello")
                val data = repo.getListOfRockets()
                println(data.toString())
                println("FOOL")
                withContext(Dispatchers.Main){
                    allRockets.value = data
                }
            } catch (ex:Exception){}
        }
    }
}