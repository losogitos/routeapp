package com.ciupa.routeapp.extension

import android.widget.TextView
import com.ciupa.routeapp.R
import com.ciupa.routeapp.model.TransportCardType
import com.ciupa.routeapp.model.TransportType

fun TextView.setRouteText(card: TransportCardType) {
    if (card is TransportCardType.EndTransportCard) {
        setText(R.string.you_have_arrived)
        return
    }
    if (card !is TransportCardType.TransportCard) return
    card.startDestiny
    val road = context.getString(R.string.take_trip, card.transport.transportType.transportName,
    card.transport.number ?: "", card.startDestiny.destinyName, card.endDestiny.destinyName)
    val geatseat = when (card.transport.transportType) {
            TransportType.TRAIN, TransportType.AIRPORTBUS ->
                card.transport.seat?.run {context.getString(R.string.sit_in, this)} ?:
                context.getString(R.string.no_seat)
            TransportType.FLIGHT ->
                context.getString(R.string.gate_seat, card.transport.gate?: "",
                    card.transport.seat ?: "")
        }
    val baggage = card.transport.baggage ?: "" // dynamic string building needed for translation
    setText("$road $geatseat $baggage")
}