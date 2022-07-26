package com.example.spacex.data.model

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
    var rockets: ArrayList<String> = arrayListOf(),
    var timezone: String?,
    var launches: ArrayList<String> = arrayListOf(),
    var status: String?,
    var details: String?,
    var id: String?
) {

}

data class Images(
    var large: ArrayList<String> = arrayListOf()
)