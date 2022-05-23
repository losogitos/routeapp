package com.ciupa.routeapp.data

import com.ciupa.routeapp.model.Destiny
import com.ciupa.routeapp.model.Transport
import com.ciupa.routeapp.model.TransportCardType
import com.ciupa.routeapp.model.TransportType

class CardsRepository {
    fun loadCardsStack(): List<TransportCardType.TransportCard> {
        val card1 = TransportCardType.TransportCard(
            1,
            Destiny.MADRIT,
            Destiny.BARCELONA,
            Transport(
                TransportType.TRAIN,
                number = "78A",
                seat = "45B"
            )
        )
        val card2 = TransportCardType.TransportCard(
            2,
            Destiny.BARCELONA,
            Destiny.GIRONA,
            Transport(
                TransportType.AIRPORTBUS,
            )
        )
        val card3 = TransportCardType.TransportCard(
            3,
            Destiny.GIRONA,
            Destiny.LONDON,
            Transport(
                TransportType.FLIGHT,
                "SK455",
                "45B",
                "3A",
                "Baggage drop at ticket counter 344."
            )
        )
        val card4 = TransportCardType.TransportCard(
            4,
            Destiny.LONDON,
            Destiny.NEWYORK,
            Transport(
                TransportType.FLIGHT,
                "SK22",
                "22",
                "7B",
                "Baggage will we automatically transferred from your last leg."
            )
        )
        return listOf(card3, card2, card4, card1)
    }
}