package com.example.spacex.domain.ui_model

data class UiLaunchpad(
    var images: Images? = Images(),
    var name: String? = null,
    var fullName: String? = null,
    var locality: String? = null,
    var region: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var launchAttempts: Int? = null,
    var launchSuccesses: Int? = null,
    var rockets: ArrayList<String> = arrayListOf(),
    var timezone: String? = null,
    var launches: ArrayList<String> = arrayListOf(),
    var status: String? = null,
    var details: String? = null,
    var id: String? = null
) {

}

data class Images(
    var large: ArrayList<String> = arrayListOf()
)