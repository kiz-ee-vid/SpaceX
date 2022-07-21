//package com.example.spacex.data.model
//
//import android.os.Parcelable
//import kotlinx.parcelize.Parcelize
//import java.io.Serializable
//import java.util.ArrayList
//
//@Parcelize
//data class Rocket(
//    var height: Height? = null,
//    var diameter: Diameter? = null,
//    var mass: Mass? = null,
//    var first_stage: FirstStage? = null,
//    var second_stage: SecondStage? = null,
//    var engines: Engines? = null,
//    var landingLegs: LandingLegs? = null,
//    var payload_weights: ArrayList<String?> = ArrayList<String?>(),
//    var flickr_images: ArrayList<String> = ArrayList<String>(),
//    var name: String? = null,
//    var type: String? = null,
//    var active: Boolean? = null,
//    var stages: Int? = null,
//    var boosters: Int? = null,
//    var cost_per_launch: Int? = null,
//    var success_rate_pct: Int? = null,
//    var first_flight: String? = null,
//    var country: String? = null,
//    var company: String? = null,
//    var wikipedia: String? = null,
//    var description: String? = null,
//    var id: String? = null
//): Parcelable
//
//@Parcelize
//data class Height(
//    var meters: Double? = null,
//    var feet: Double? = null,
//): Parcelable
//
//@Parcelize
//data class Diameter(
//    var meters: Double? = null,
//    var feet: Double? = null
//): Parcelable
//
//@Parcelize
//data class Mass(
//    var kg: Int? = null,
//    var lb: Int? = null
//): Parcelable
//
//@Parcelize
//data class FirstStage(
//    var thrust_sea_level: ThrustSeaLevel? = null,
//    var thrust_vacuum: ThrustVacuum? = null,
//    var reusable: Boolean? = null,
//    var engines: Int? = null,
//    var fuel_amount_tons: Double? = null,
//    var burn_time_sec: Int? = null
//): Parcelable
//
//@Parcelize
//data class ThrustSeaLevel(
//    var kN: Int? = null,
//    var lbf: Int? = null
//): Parcelable
//
//@Parcelize
//data class ThrustVacuum(
//    var kN: Int? = null,
//    var lbf: Int? = null
//): Parcelable
//
//@Parcelize
//data class SecondStage(
//    var thrust: Thrust? = null,
//    var payloads: Payloads? = null,
//    var reusable: Boolean? = null,
//    var engines: Int? = null,
//    var fuel_amount_tons: Double? = null,
//    var burn_time_sec: Int? = null
//): Parcelable
//
//@Parcelize
//data class Thrust(
//    var kN: Int? = null,
//    var lbf: Int? = null
//): Parcelable
//
//@Parcelize
//data class Payloads(
//    var composite_fairing: CompositeFairing? = null,
//    var option1: String? = null
//): Parcelable
//
//@Parcelize
//data class CompositeFairing(
//    var height: Height? = null,
//    var diameter: Diameter? = null
//): Parcelable
//
//@Parcelize
//data class Engines(
//    var isp: Isp? = null,
//    var thrust_sea_level: ThrustSeaLevel? = null,
//    var thrust_vacuum: ThrustVacuum? = null,
//    var number: Int? = null,
//    var type: String? = null,
//    var version: String? = null,
//    var layout: String? = null,
//    var engine_loss_max: Int? = null,
//    var propellant1: String? = null,
//    var propellant2: String? = null,
//    var thrust_to_weight: Double? = null
//): Parcelable
//
//@Parcelize
//data class Isp(
//    var sea_level: Int? = null,
//    var vacuum: Int? = null
//): Parcelable
//
//@Parcelize
//data class LandingLegs(
//    var number: Int? = null,
//    var material: String? = null
//): Parcelable