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
        CoroutineScope(Dispatchers.IO).launch() {
            try{
                val data = repo.getListOfRockets()
                println(data.toString())
                withContext(Dispatchers.Main){
                    allRockets.postValue(data)
                }
            } catch (ex:Exception){}
        }
    }

    fun filterByFirstLaunch(){
        allRockets.value?.sortBy { it.first_flight }
        allRockets.postValue(allRockets.value)
    }

    fun filterByLaunchCost(){
        allRockets.value?.sortBy { it.cost_per_launch }
        allRockets.postValue(allRockets.value)
    }

    fun filterBySuccessRate(){
        allRockets.value?.sortByDescending { it.success_rate_pct }
        allRockets.postValue(allRockets.value)
    }
}