package com.example.spacex.data.model

import java.io.Serializable
import java.util.ArrayList

data class Rocket(
    var height: Height?,
    var diameter: Diameter?,
    var mass: Mass?,
    var first_stage: FirstStage?,
    var second_stage: SecondStage?,
    var engines: Engines?,
    var landingLegs: LandingLegs?,
    var payload_weights: MutableList<Any?> = ArrayList<Any?>().toMutableList(),
    var flickr_images: MutableList<String?> = ArrayList<String?>().toMutableList(),
    var name: String?,
    var type: String?,
    var active: Boolean?,
    var stages: Int?,
    var boosters: Int?,
    var cost_per_launch: Int?,
    var success_rate_pct: Int?,
    var first_flight: String?,
    var country: String?,
    var company: String?,
    var wikipedia: String?,
    var description: String?,
    var id: String?
)

data class Height(
    var meters: Double?,
    var feet: Double?,
)

class Diameter(
    var meters: Double?,
    var feet: Double?
)

class Mass(
    var kg: Int?,
    var lb: Int?
)

class FirstStage(
    var thrust_sea_level: ThrustSeaLevel?,
    var thrust_vacuum: ThrustVacuum?,
    var reusable: Boolean?,
    var engines: Int?,
    var fuel_amount_tons: Double?,
    var burn_time_sec: Int?
)

class ThrustSeaLevel(
    var kN: Int?,
    var lbf: Int?
)

class ThrustVacuum(
    var kN: Int?,
    var lbf: Int?
)

class SecondStage(
    var thrust: Thrust?,
    var payloads: Payloads?,
    var reusable: Boolean?,
    var engines: Int?,
    var fuel_amount_tons: Double?,
    var burn_time_sec: Int?
)

class Thrust(
    var kN: Int?,
    var lbf: Int?
)

class Payloads(
    var composite_fairing: CompositeFairing?,
    var option1: String?,
)

class CompositeFairing: Serializable {
    var height: Height? = null
    var diameter: Diameter? = null
}

class Engines(
    var isp: Isp?,
    var thrust_sea_level: ThrustSeaLevel?,
    var thrust_vacuum: ThrustVacuum?,
    var number: Int?,
    var type: String?,
    var version: String?,
    var layout: String?,
    var engine_loss_max: Int?,
    var propellant1: String?,
    var propellant2: String?,
    var thrust_to_weight: Double?
)

class Isp(
    var sea_level: Int?,
    var vacuum: Int?
)

class LandingLegs(
    var number: Int?,
    var material: Any?
)