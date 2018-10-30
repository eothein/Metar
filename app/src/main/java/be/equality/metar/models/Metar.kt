package be.equality.metar.models


/**
 * Data class representing a Metar. METAR is a format for reporting weather information.
 * A METAR weather report is predominantly used by pilots in fulfillment of a part of a
 * pre-flight weather briefing, and by meteorologists, who use aggregated METAR
 * information to assist in weather forecasting.
 *
 * @property id The id of this metar
 * @property rawMetar The raw code of the metar
 * @property airport The airport where this Metar belongs to
 * @property dayOfMonth The day of the month for this Metar
 * @property time The time of this Metar
 * @property windDirection The wind direction in Metar format (e.g. 12012MPS indicates
 * the wind direction is from 120° (east-southeast) at a speed of 12 m/s (23 knots; 27 mph; 44 km/h)
 * @property windSpeed Speed of the wind
 * @property gusts The possible gusts of the weather
 * @property lineOfSight The line of sight (expressed in meter)
 */
data class Metar(val id: Long, val rawMetar : String, val airport: Airport,
                 val dayOfMonth : Int, val time : Int, val windDirection : Int,
                 val windSpeed : Int, val gusts: Int, val lineOfSight : Int
                 ) {
}