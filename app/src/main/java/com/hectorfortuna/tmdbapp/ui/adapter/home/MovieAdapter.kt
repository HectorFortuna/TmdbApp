package com.hectorfortuna.tmdbapp.ui.adapter.home

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import kotlin.math.log

class MovieAdapter(
    private val itemClick: ((item: Result) -> Unit),
    private val onListChanged: (() -> Unit)
) : ListAdapter<Result, MovieViewHolder>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent,itemClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(getItem(position))
        if(currentList.count()-1 == position){
            onListChanged.invoke()
        }
    }

    companion object{
        private val diffCallback = object: DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }
}
