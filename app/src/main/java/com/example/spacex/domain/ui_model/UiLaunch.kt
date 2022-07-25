package com.example.spacex.domain.ui_model

import android.os.Parcelable
import com.example.spacex.data.model.Patch
import com.example.spacex.data.model.Reddit
import kotlinx.parcelize.Parcelize

@Parcelize
class UiLaunch(
    var fairings: UiFairings? = UiFairings(),
    var links: UiLinks? = UiLinks(),
    var staticFireDateUtc: String? = null,
    var staticFireDateUnix: Int? = null,
    var net: Boolean? = null,
    var window: Int? = null,
    var rocket: String? = null,
    var success: Boolean? = null,
    //var failures: ArrayList<UiFailures> = arrayListOf(),
    var details: String? = null,
    var launchpad: String? = null,
    var flightNumber: Int? = null,
    var name: String? = null,
    var dateUtc: String? = null,
    var dateUnix: Int? = null,
    var dateLocal: String? = null,
    var datePrecision: String? = null,
    var upcoming: Boolean? = null,
    //var cores: ArrayList<UiCores> = arrayListOf(),
    var autoUpdate: Boolean? = null,
    var tbd: Boolean? = null,
    var launchLibraryId: String? = null,
    var id: String? = null
): Parcelable

@Parcelize
data class UiFairings(
    var reused: Boolean? = null,
    var recoveryAttempt: Boolean? = null,
    var recovered: Boolean? = null,
    var ships: ArrayList<String>? = arrayListOf()
): Parcelable

@Parcelize
data class UiPatch(
    var small: String? = null,
    var large: String? = null
): Parcelable

@Parcelize
data class UiReddit(
    var campaign: String? = null,
    var launch: String? = null,
    var media: String? = null,
    var recovery: String? = null
): Parcelable

@Parcelize
data class UiFlickr(
    var small: ArrayList<String>? = arrayListOf(),
    var original: ArrayList<String>? = arrayListOf()
): Parcelable

@Parcelize
data class UiLinks(
    var patch: UiPatch? = UiPatch(),
    var reddit: UiReddit? = UiReddit(),
    var flickr: UiFlickr? = UiFlickr(),
    var presskit: String? = null,
    var webcast: String? = null,
    var youtubeId: String? = null,
    var article: String? = null,
    var wikipedia: String? = null
): Parcelable

@Parcelize
data class UiFailures(
    var time: Int? = null,
    var altitude: String? = null,
    var reason: String? = null
): Parcelable

@Parcelize
data class UiCores(
    var core: String? = null,
    var flight: Int? = null,
    var gridfins: Boolean? = null,
    var legs: Boolean? = null,
    var reused: Boolean? = null,
    var landingAttempt: Boolean? = null,
    var landingSuccess: String? = null,
    var landingType: String? = null,
    var landpad: String? = null
): Parcelable