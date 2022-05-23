package com.ciupa.routeapp

import com.ciupa.routeapp.domain.RouteInteractor
import com.ciupa.routeapp.model.Destiny
import com.ciupa.routeapp.model.Transport
import com.ciupa.routeapp.model.TransportCardType
import com.ciupa.routeapp.model.TransportType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class RouteInteractorTest (private val cards: List<TransportCardType.TransportCard>) {

    private lateinit var routeInteractor : RouteInteractor

    companion object {
        private val card1 = TransportCardType.TransportCard(
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

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            // GIVEN
            return listOf(
                arrayOf(listOf(card3, card2, card4, card1)),
                arrayOf(listOf(card2, card1, card3, card4)),
                arrayOf(listOf(card1, card2, card4, card3)),
                arrayOf(listOf(card4, card1, card3, card2)),
            )
        }
    }

    @Before
    fun setUp() {
        routeInteractor = RouteInteractor()
    }

    @Test
    fun `Executing findRoute for random set of trip cards returns sorted cards reflecting a route`() {
        // When the interactor runs the algorithm for searching of route
        val sortedCards = routeInteractor.findRoute(cards)
        // Then cards are sorted in a proper order
        sortedCards.subList(0, sortedCards.lastIndex - 1).forEachIndexed {index, transportCard ->
            assertEquals(transportCard.endDestiny, sortedCards[index + 1].startDestiny)
        }
    }
}