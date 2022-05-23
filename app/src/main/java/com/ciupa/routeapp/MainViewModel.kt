package com.ciupa.routeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciupa.routeapp.domain.RouteInteractor
import com.ciupa.routeapp.model.TransportCardType
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _cardList = MutableLiveData<List<TransportCardType.TransportCard>>(emptyList())
    val cardList: LiveData<List<TransportCardType.TransportCard>> = _cardList
    // Injected in normal project
    private val routeInteractor = RouteInteractor()

    init {
        viewModelScope.launch {
            val cards = routeInteractor.fetchCards()
            _cardList.value = cards
        }
    }

    fun onSortClick() {
        cardList.value?.let {
            val sortedCards = routeInteractor.findRoute(it)
            Log.d("Result", "Sorted elements: $sortedCards")
            _cardList.value = sortedCards
        }
    }
}