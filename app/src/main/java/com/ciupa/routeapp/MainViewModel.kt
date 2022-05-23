package com.ciupa.routeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciupa.routeapp.model.Destiny
import com.ciupa.routeapp.model.Transport
import com.ciupa.routeapp.model.TransportCard
import com.ciupa.routeapp.model.TransportType

class MainViewModel : ViewModel() {

    private val _cardList = MutableLiveData<List<String>>(emptyList())
    val cardList: LiveData<List<String>> = _cardList
    private val cards : List<TransportCard>

    init {
        cards = buildCardsStack()
        val cardStrings = cards.map { it.toString() }
        _cardList.value = cardStrings
    }

    fun onSortClick() {
        val sortedCards = findRoute(cards)
        Log.d("Result", "Sorted elements: $sortedCards")
        _cardList.value = sortedCards.map { it.toString() }
    }

    class SortingBlock (
        val cards: MutableList<TransportCard>
    )

    private fun findRoute(cards: List<TransportCard>) : List<TransportCard> {
        val blocks = mutableSetOf<SortingBlock>()
        for (card in cards) {
            blocks.attachToBlocks(SortingBlock(mutableListOf(card)))
        }
        return joinBlocks(blocks).cards
    }

    private fun joinBlocks(blocks: MutableSet<SortingBlock>) : SortingBlock {
        if (blocks.size == 1) return blocks.first()
        val nextBlocks = mutableSetOf<SortingBlock>()
        for (block in blocks) {
            nextBlocks.attachToBlocks(block)
        }
        return joinBlocks(nextBlocks)
    }

    /**
     * Tries to find a block to which given new block matches.
     * If thre is no block to which the new block matches, new block
     * is created.
     */
    private fun MutableSet<SortingBlock>.attachToBlocks(newBlock: SortingBlock) {
        for (block in this) {
            when {
                newBlock.cards.first().startDestiny == block.cards.last().endDestiny -> {
                    block.cards.addAll(newBlock.cards)
                    return
                }
                newBlock.cards.last().endDestiny == block.cards.first().startDestiny -> {
                    block.cards.addAll(0, newBlock.cards)
                    return
                }
            }
        }
        add(newBlock)
    }

    private fun buildCardsStack(): List<TransportCard> {
        val card1 = TransportCard(
            1,
            Destiny.MADRIT,
            Destiny.BARCELONA,
            Transport(
                TransportType.TRAIN,
                "78A",
                "45B"
            )
        )
        val card2 = TransportCard(
            2,
            Destiny.BARCELONA,
            Destiny.GIRONA,
            Transport(
                TransportType.AIRPORTBUS,
            )
        )
        val card3 = TransportCard(
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
        val card4 = TransportCard(
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