package com.ciupa.routeapp.model



data class Transport (
    val transportType: TransportType,
    val number: String? = null,
    val gate: String? = null,
    val seat: String? = null,
    val baggage: String? = null
)

sealed class TransportCardType {
    data class TransportCard (
        val id: Int,
        val startDestiny: Destiny,
        val endDestiny: Destiny,
        val transport: Transport) : TransportCardType()
    object EndTransportCard : TransportCardType()
}