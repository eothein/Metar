package be.equality.metar.models


/**
 * Aiport data class, representing an Airport
 *
 * @property id The id of this Airport
 * @property locationIndicator Four letter code, following the standards of ICAO and WMO
 * @property description Short description of this airport (e.g. its name)
 */
data class Airport(val id : Int, val locationIndicator : String, val description : String ) {

}