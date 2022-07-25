package com.example.spacex.data.model

import com.example.spacex.domain.ui_model.*
import com.google.gson.annotations.SerializedName

data class Launch(
    var fairings: Fairings?,
    var links: Links?,
    @SerializedName("static_fire_date_utc") var staticFireDateUtc: String?,
    @SerializedName("static_fire_date_unix") var staticFireDateUnix: Int?,
    var net: Boolean?,
    var window: Int?,
    var rocket: String?,
    var success: Boolean?,
    var failures: ArrayList<Failures> = ArrayList(),
    var details: String?,
    var launchpad: String?,
    @SerializedName("flight_number") var flightNumber: Int?,
    var name: String?,
    @SerializedName("date_utc") var dateUtc: String?,
    @SerializedName("date_unix") var dateUnix: Int?,
    @SerializedName("date_local") var dateLocal: String?,
    @SerializedName("date_precision") var datePrecision: String?,
    var upcoming: Boolean?,
    var cores: ArrayList<Cores> = ArrayList(),
    @SerializedName("auto_update") var autoUpdate: Boolean?,
    var tbd: Boolean?,
    @SerializedName("launch_library_id") var launchLibraryId: String?,
    var id: String?
) {
    fun mapToUiLaunch() =
        UiLaunch(
            UiFairings(
                fairings?.reused,
                fairings?.recoveryAttempt,
                fairings?.recovered,
                fairings?.ships
            ),
            UiLinks(
                UiPatch(links?.patch?.small, links?.patch?.large), UiReddit(
                    links?.reddit?.campaign, links?.reddit?.launch,
                    links?.reddit?.media,
                    links?.reddit?.recovery
                ), UiFlickr(links?.flickr?.small, links?.flickr?.original),
                links?.presskit,
                links?.webcast,
                links?.youtubeId,
                links?.article,
                links?.wikipedia
            ),
            staticFireDateUtc,
            staticFireDateUnix,
            net,
            window,
            rocket,
            success,
            details,
            launchpad,
            flightNumber,
            name,
            dateUtc,
            dateUnix,
            dateLocal,
            datePrecision,
            upcoming,
            autoUpdate,
            tbd,
            launchLibraryId,
            id
        )
}

data class Fairings(
    var reused: Boolean?,
    @SerializedName("recovery_attempt") var recoveryAttempt: Boolean?,
    var recovered: Boolean?,
    @SerializedName("ships") var ships: ArrayList<String> = ArrayList()
)

data class Patch(
    var small: String?,
    var large: String?
)

data class Reddit(
    var campaign: String?,
    var launch: String?,
    var media: String?,
    var recovery: String?
)

data class Flickr(
    var small: ArrayList<String>,
    var original: ArrayList<String> = ArrayList()
)

data class Links(
    var patch: Patch?,
    var reddit: Reddit?,
    var flickr: Flickr?,
    var presskit: String?,
    var webcast: String?,
    @SerializedName("youtube_id") var youtubeId: String?,
    var article: String?,
    var wikipedia: String?
)

data class Failures(
    var time: Int?,
    var altitude: String?,
    var reason: String?
)

data class Cores(
    var core: String?,
    var flight: Int?,
    var gridfins: Boolean?,
    var legs: Boolean?,
    var reused: Boolean?,
    @SerializedName("landing_attempt") var landingAttempt: Boolean?,
    @SerializedName("landing_success") var landingSuccess: String?,
    @SerializedName("landing_type") var landingType: String?,
    var landpad: String?
)