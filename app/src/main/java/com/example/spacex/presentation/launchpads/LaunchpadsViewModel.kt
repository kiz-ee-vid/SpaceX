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

    init {
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                repo.getListOfLaunchpads()?.forEach {
                    allLaunchpads.add(it.mapToUiLaunch())
                }
                withContext(Dispatchers.Main) {
                    listLaunchpads.value = allLaunchpads
                }
            } catch (ex: Exception) {
            }
        }
    }
}