package com.hectorfortuna.tmdbapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hectorfortuna.tmdbapp.data.network.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.MovieItemBinding
import com.hectorfortuna.tmdbapp.util.imageUrl

class MovieViewHolder(private val movieItemBinding: MovieItemBinding):RecyclerView.ViewHolder(movieItemBinding.root) {

    fun bindView(moviesResult: Result) {
        movieItemBinding.run {
            txtMovieName.text = moviesResult.title
            txtMovieDuration.text = moviesResult.releaseDate

            Glide.with(itemView)
                .load("${imageUrl()}${moviesResult.posterPath}")
                .centerCrop()
                .into(imgPosterMovie)
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = MovieItemBinding.inflate(inflater, parent, false)
            return MovieViewHolder(itemBinding)
        }
    }
}