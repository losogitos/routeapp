package com.ciupa.routeapp.domain

import com.ciupa.routeapp.data.CardsRepository
import com.ciupa.routeapp.model.TransportCard

class RouteInteractor {

    // Injected in normal project
    private val cardsRepository = CardsRepository()

    fun fetchCards() : List<TransportCard> {
        return cardsRepository.loadCardsStack()
    }

    fun findRoute(cards: List<TransportCard>) : List<TransportCard> {
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

    class SortingBlock (
        val cards: MutableList<TransportCard>
    )
}