package com.example.spacex.domain.ui_model

import android.os.Parcelable
import com.example.spacex.data.model.ThrustSeaLevel
import com.example.spacex.data.model.ThrustVacuum
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Parcelize
data class UiRocket(
    var height: UiHeight? = null,
    var diameter: UiDiameter? = null,
    var mass: UiMass? = null,
    var first_stage: UiFirstStage? = null,
    var second_stage: UiSecondStage? = null,
    var engines: UiEngines? = null,
    var landingLegs: UiLandingLegs? = null,
    //var payload_weights: ArrayList<String?> = ArrayList<String?>(),
    var flickr_images: ArrayList<String> = ArrayList<String>(),
    var name: String? = null,
    var type: String? = null,
    //var active: Boolean? = null,
    var stages: Int? = null,
    var boosters: Int? = null,
    var cost_per_launch: Int? = null,
    var success_rate_pct: Int? = null,
    var first_flight: String? = null,
    var country: String? = null,
    var company: String? = null,
    var wikipedia: String? = null,
    var description: String? = null,
    var id: String? = null
): Parcelable

@Parcelize
data class UiHeight(
    var meters: Double? = null,
    var feet: Double? = null,
): Parcelable

@Parcelize
data class UiDiameter(
    var meters: Double? = null,
    var feet: Double? = null
): Parcelable

@Parcelize
data class UiMass(
    var kg: Int? = null,
    var lb: Int? = null
): Parcelable

@Parcelize
data class UiFirstStage(
    var thrust_sea_level: UiThrustSeaLevel? = null,
    var thrust_vacuum: UiThrustVacuum? = null,
    var reusable: Boolean? = null,
    var engines: Int? = null,
    var fuel_amount_tons: Double? = null,
    var burn_time_sec: Int? = null
): Parcelable

@Parcelize
data class UiThrustSeaLevel(
    var kN: Int? = null,
    var lbf: Int? = null
): Parcelable

@Parcelize
data class UiThrustVacuum(
    var kN: Int? = null,
    var lbf: Int? = null
): Parcelable

@Parcelize
data class UiSecondStage(
    var thrust: UiThrust? = null,
    var payloads: UiPayloads? = null,
    var reusable: Boolean? = null,
    var engines: Int? = null,
    var fuel_amount_tons: Double? = null,
    var burn_time_sec: Int? = null
): Parcelable

@Parcelize
data class UiThrust(
    var kN: Int? = null,
    var lbf: Int? = null
): Parcelable

@Parcelize
data class UiPayloads(
    var composite_fairing: UiCompositeFairing? = null,
    var option1: String? = null
): Parcelable

@Parcelize
data class UiCompositeFairing(
    var uiHeight: UiHeight? = null,
    var uiDiameter: UiDiameter? = null
): Parcelable

@Parcelize
data class UiEngines(
    var isp: UiIsp? = null,
    var thrust_sea_level: UiThrustSeaLevel? = null,
    var thrust_vacuum: UiThrustVacuum? = null,
    var number: Int? = null,
    var type: String? = null,
    var version: String? = null,
    var layout: String? = null,
    var engine_loss_max: Int? = null,
    var propellant1: String? = null,
    var propellant2: String? = null,
    var thrust_to_weight: Double? = null
): Parcelable

@Parcelize
data class UiIsp(
    var sea_level: Int? = null,
    var vacuum: Int? = null
): Parcelable

@Parcelize
data class UiLandingLegs(
    var number: Int? = null,
    var material: String? = null
): Parcelable


