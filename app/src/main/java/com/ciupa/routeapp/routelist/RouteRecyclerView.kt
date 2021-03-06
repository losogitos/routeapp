package com.ciupa.routeapp.routelist

import android.content.Context
import android.util.AttributeSet
import com.ciupa.routeapp.model.TransportCardType

class RouteRecyclerView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
androidx.recyclerview.widget.RecyclerView(context, attrs, defStyle) {

    private val adapter = RouteAdapter()

    init {
        addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        setAdapter(adapter)
    }

    fun setItems(data: List<TransportCardType.TransportCard>) {
        adapter.setItems(data)
    }
}