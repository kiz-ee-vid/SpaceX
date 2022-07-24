package com.example.spacex.data.model

import com.example.spacex.domain.ui_model.*
import java.util.ArrayList

data class Rocket(
    var height: Height?,
    var diameter: Diameter?,
    var mass: Mass?,
    var first_stage: FirstStage?,
    var second_stage: SecondStage?,
    var engines: Engines?,
    var landing_legs: LandingLegs?,
    var payload_weights: ArrayList<Any?> = ArrayList<Any?>(),
    var flickr_images: ArrayList<String> = ArrayList<String>(),
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
) {
    fun mapToUiRocket() =
        UiRocket(
            UiHeight(height?.meters, height?.feet),
            UiDiameter(diameter?.meters, diameter?.feet),
            UiMass(mass?.kg, mass?.lb),
            UiFirstStage(
                UiThrustSeaLevel(
                    first_stage?.thrust_sea_level?.kN,
                    first_stage?.thrust_sea_level?.lbf
                ),
                UiThrustVacuum(first_stage?.thrust_vacuum?.kN, first_stage?.thrust_vacuum?.lbf),
                first_stage?.reusable,
                first_stage?.engines,
                first_stage?.fuel_amount_tons,
                first_stage?.burn_time_sec
            ),
            UiSecondStage(
                UiThrust(second_stage?.thrust?.kN, second_stage?.thrust?.lbf),
                UiPayloads(
                    UiCompositeFairing(
                        UiHeight(height?.meters, height?.feet),
                        UiDiameter(diameter?.meters, diameter?.feet)
                    ),
                    second_stage?.payloads?.option1
                ),
                second_stage?.reusable,
                second_stage?.engines,
                second_stage?.fuel_amount_tons,
                second_stage?.burn_time_sec
            ),
            UiEngines(
                UiIsp(engines?.isp?.sea_level, engines?.isp?.vacuum),
                UiThrustSeaLevel(engines?.thrust_sea_level?.kN, engines?.thrust_sea_level?.lbf),
                UiThrustVacuum(engines?.thrust_vacuum?.kN, engines?.thrust_vacuum?.lbf),
                engines?.number,
                engines?.type,
                engines?.version,
                engines?.layout,
                engines?.engine_loss_max,
                engines?.propellant_1,
                engines?.propellant_2,
                engines?.thrust_to_weight
            ),
            UiLandingLegs(landing_legs?.number, landing_legs?.material),
            flickr_images,
            name,
            type,
            stages,
            boosters,
            cost_per_launch,
            success_rate_pct,
            first_flight,
            country,
            company,
            wikipedia,
            description,
            id
        )
}

data class Height(
    var meters: Double?,
    var feet: Double?,
)

data class Diameter(
    var meters: Double?,
    var feet: Double?
)

data class Mass(
    var kg: Int?,
    var lb: Int?
)

data class FirstStage(
    var thrust_sea_level: ThrustSeaLevel?,
    var thrust_vacuum: ThrustVacuum?,
    var reusable: Boolean?,
    var engines: Int?,
    var fuel_amount_tons: Double?,
    var burn_time_sec: Int?
)

data class ThrustSeaLevel(
    var kN: Int?,
    var lbf: Int?
)

data class ThrustVacuum(
    var kN: Int?,
    var lbf: Int?
)

data class SecondStage(
    var thrust: Thrust?,
    var payloads: Payloads?,
    var reusable: Boolean?,
    var engines: Int?,
    var fuel_amount_tons: Double?,
    var burn_time_sec: Int?
)

data class Thrust(
    var kN: Int?,
    var lbf: Int?
)

data class Payloads(
    var composite_fairing: CompositeFairing?,
    var option1: String?,
)

data class CompositeFairing(
    var height: Height?,
    var diameter: Diameter?
)

data class Engines(
    var isp: Isp?,
    var thrust_sea_level: ThrustSeaLevel?,
    var thrust_vacuum: ThrustVacuum?,
    var number: Int?,
    var type: String?,
    var version: String?,
    var layout: String?,
    var engine_loss_max: Int?,
    var propellant_1: String?,
    var propellant_2: String?,
    var thrust_to_weight: Double?
)

data class Isp(
    var sea_level: Int?,
    var vacuum: Int?
)

data class LandingLegs(
    var number: Int?,
    var material: String?
)