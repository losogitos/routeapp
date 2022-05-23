package com.ciupa.routeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciupa.routeapp.domain.RouteInteractor
import com.ciupa.routeapp.model.TransportCard

class MainViewModel : ViewModel() {

    private val _cardList = MutableLiveData<List<String>>(emptyList())
    val cardList: LiveData<List<String>> = _cardList
    private val cards : List<TransportCard>
    // Injected in normal project
    private val routeInteractor = RouteInteractor()

    init {
        cards = routeInteractor.fetchCards()
        val cardStrings = cards.map { it.toString() }
        _cardList.value = cardStrings
    }

    fun onSortClick() {
        val sortedCards = routeInteractor.findRoute(cards)
        Log.d("Result", "Sorted elements: $sortedCards")
        _cardList.value = sortedCards.map { it.toString() }
    }
}