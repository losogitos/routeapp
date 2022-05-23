package com.ciupa.routeapp.routelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciupa.routeapp.databinding.TextRowItemBinding
import com.ciupa.routeapp.extension.setRouteText
import com.ciupa.routeapp.model.TransportCardType

class RouteAdapter :
    RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    private val items: MutableList<TransportCardType.TransportCard> = mutableListOf()

    class ViewHolder(val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: TransportCardType) {
            binding.routeDescription.setRouteText(entry)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when {
            position == itemCount - 1 -> viewHolder.bind(TransportCardType.EndTransportCard)
            else -> viewHolder.bind(items[position])
        }
    }

    override fun getItemCount() = items.size + 1

    fun setItems(data: List<TransportCardType.TransportCard>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}