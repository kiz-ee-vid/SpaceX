package com.example.spacex.domain.ui_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiLaunchpad(
    var images: UiImages? = UiImages(),
    var name: String? = null,
    var fullName: String? = null,
    var locality: String? = null,
    var region: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var launchAttempts: Int? = null,
    var launchSuccesses: Int? = null,
    var timezone: String? = null,
    var status: String? = null,
    var details: String? = null,
    var id: String? = null
): Parcelable

@Parcelize
data class UiImages(
    var large: ArrayList<String>? = arrayListOf()
): Parcelable