package com.hectorfortuna.tmdbapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hectorfortuna.tmdbapp.databinding.MovieItemBinding
import com.hectorfortuna.tmdbapp.util.imageUrl
import com.hectorfortuna.tmdbapp.data.model.popular.Result as Result

class MovieViewHolder(
    private val movieItemBinding: MovieItemBinding,
    private val itemClick: ((item: Result) -> Unit)
    ):RecyclerView.ViewHolder(movieItemBinding.root) {

    fun bindView(moviesResult: Result) {
        movieItemBinding.run {
            txtMovieName.text = moviesResult.title
            txtMovieDuration.text = moviesResult.releaseDate

            Glide.with(itemView)
                .load("${imageUrl()}${moviesResult.posterPath}")
                .centerCrop()
                .into(imgPosterMovie)

            itemView.setOnClickListener {
                itemClick.invoke(moviesResult)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (item: Result) -> Unit): MovieViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = MovieItemBinding.inflate(inflater, parent, false)
            return MovieViewHolder(itemBinding, itemClick)
        }
    }
}