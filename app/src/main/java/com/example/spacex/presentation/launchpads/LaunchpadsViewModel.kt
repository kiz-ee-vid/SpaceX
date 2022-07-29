package com.example.spacex.presentation.launchpads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex.data.RepositoryImpl
import com.example.spacex.domain.ui_model.UiLaunch
import com.example.spacex.domain.ui_model.UiLaunchpad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LaunchpadsViewModel @Inject constructor(private val repo: RepositoryImpl) : ViewModel() {

    val listLaunchpads = MutableLiveData<ArrayList<UiLaunchpad>>()
    private var allLaunchpads: ArrayList<UiLaunchpad> = ArrayList()
    var filter = "all"

    init {
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                repo.getListOfLaunchpads()?.forEach {
                    allLaunchpads.add(it.mapToUiLaunchpad())
                }
                withContext(Dispatchers.Main) {
                    listLaunchpads.value = allLaunchpads
                }
            } catch (ex: Exception) {
            }
        }
    }

    fun filterByAll() {
        listLaunchpads.value = allLaunchpads
        filter = "all"
    }

    fun filterByActive() {
        val data = ArrayList<UiLaunchpad>()
        allLaunchpads.forEach {
            if (it.status == "active") {
                data.add(it)
            }
            filter = "active"
        }
        listLaunchpads.value = data
    }

    fun filterByRetired() {
        val data = ArrayList<UiLaunchpad>()
        allLaunchpads.forEach {
            if (it.status == "retired") {
                data.add(it)
            }
            filter = "retired"
        }
        listLaunchpads.value = data
    }
}