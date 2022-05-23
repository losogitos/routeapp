package com.ciupa.routeapp.domain

import com.ciupa.routeapp.data.CardsRepository
import com.ciupa.routeapp.model.TransportCardType

class RouteInteractor {

    // Injected in normal project
    private val cardsRepository = CardsRepository()

    fun fetchCards() : List<TransportCardType.TransportCard> {
        return cardsRepository.loadCardsStack()
    }

    /**
     * Warning: input data must be in consistent format - all cards can be linked. Otherwise
     * the algorithm will never end.
     * @todo protect the algorithm against non linkable cards
     */
    fun findRoute(cards: List<TransportCardType.TransportCard>) : List<TransportCardType.TransportCard> {
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
     * If there is no block to which the new block matches, new block
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
        val cards: MutableList<TransportCardType.TransportCard>
    )
}