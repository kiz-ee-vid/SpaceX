package com.example.spacex.data.model

import com.example.spacex.domain.ui_model.UiImages
import com.example.spacex.domain.ui_model.UiLaunchpad
import com.google.gson.annotations.SerializedName

data class Launchpad(
    var images: Images?,
    var name: String?,
    @SerializedName("full_name") var fullName: String?,
    var locality: String?,
    var region: String?,
    var latitude: Double?,
    var longitude: Double?,
    @SerializedName("launch_attempts") var launchAttempts: Int?,
    @SerializedName("launch_successes") var launchSuccesses: Int?,
    var timezone: String?,
    var status: String?,
    var details: String?,
    var id: String?
) {
    fun mapToUiLaunchpad() =
        UiLaunchpad(
            UiImages(images?.large),
            name,
            fullName,
            locality,
            region,
            latitude,
            longitude,
            launchAttempts,
            launchSuccesses,
            timezone,
            status,
            details,
            id
        )
}

data class Images(
    var large: ArrayList<String>? = arrayListOf()
)