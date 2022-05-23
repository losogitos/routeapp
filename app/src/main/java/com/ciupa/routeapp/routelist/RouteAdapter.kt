package com.ciupa.routeapp.routelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciupa.routeapp.databinding.TextRowItemBinding
import com.ciupa.routeapp.model.TransportCard

class RouteAdapter :
    RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    private val items: MutableList<TransportCard> = mutableListOf()

    class ViewHolder(val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: TransportCard) {
            binding.routeDescription.text = entry.toString()
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(data: List<TransportCard>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}