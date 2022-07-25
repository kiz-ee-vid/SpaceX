package com.example.spacex.presentation.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex.data.RepositoryImpl
import com.example.spacex.data.model.Launch
import com.example.spacex.data.model.Rocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(private val repo: RepositoryImpl) : ViewModel() {

    val listLaunches = MutableLiveData<ArrayList<Launch>>()
    private var allLaunches: ArrayList<Launch>? = null
    val filter = "All"

    init {
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                allLaunches = repo.getListOfLaunches()
                withContext(Dispatchers.Main) {
                    listLaunches.value = allLaunches
                }
            } catch (ex: Exception) {
            }
        }
    }

    fun filterByAll() {
        listLaunches.value = allLaunches
    }

    fun filterByPast() {
        val data = ArrayList<Launch>()
        allLaunches?.forEach {
            if (it.upcoming == false) {
                data.add(it)
            }
        }
        listLaunches.value = data
    }

    fun filterByFuture() {
        val data = ArrayList<Launch>()
        allLaunches?.forEach {
            if (it.upcoming == true) {
                data.add(it)
            }
        }
        listLaunches.value = data
    }
}