package com.ciupa.routeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciupa.routeapp.domain.RouteInteractor
import com.ciupa.routeapp.model.TransportCardType

class MainViewModel : ViewModel() {

    private val _cardList = MutableLiveData<List<TransportCardType.TransportCard>>(emptyList())
    val cardList: LiveData<List<TransportCardType.TransportCard>> = _cardList
    private val cards : List<TransportCardType.TransportCard>
    // Injected in normal project
    private val routeInteractor = RouteInteractor()

    init {
        cards = routeInteractor.fetchCards()
        _cardList.value = cards
    }

    fun onSortClick() {
        val sortedCards = routeInteractor.findRoute(cards)
        Log.d("Result", "Sorted elements: $sortedCards")
        _cardList.value = sortedCards
    }
}