package com.hectorfortuna.tmdbapp.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.MovieItemBinding
import com.hectorfortuna.tmdbapp.util.imageUrl

class MovieAdapter(
    private val movieResults: List<Result>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = movieResults.count()

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(movieResults[position])
    }

    class MovieViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(moviesResult: Result) {
            binding.run {
                txtMovieName.text = moviesResult.title
                txtMovieDuration.text = moviesResult.releaseDate

                Glide.with(itemView)
                    .load("${imageUrl()}${moviesResult.posterPath}")
                    .centerCrop()
                    .into(imgPosterMovie)
            }
        }

    }
}
