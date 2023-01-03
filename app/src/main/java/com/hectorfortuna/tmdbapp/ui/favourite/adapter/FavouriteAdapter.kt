package com.hectorfortuna.tmdbapp.ui.favourite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.MovieItemBinding
import com.hectorfortuna.tmdbapp.util.imageUrl
import kotlin.reflect.KFunction0

class FavouriteAdapter(
    private val results: List<MovieDetails>,
    private val itemClick: ((item:MovieDetails) -> Unit),
    private val longClick: ((item: MovieDetails) -> Unit)? = null
) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding,itemClick, longClick)
    }

    override fun getItemCount() = results.count()

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bindView(results[position])
    }

    class FavouriteViewHolder(
        private val binding: MovieItemBinding,
        private val itemClick: (item: MovieDetails) -> Unit,
        private val longClick: ((item: MovieDetails) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(movies: MovieDetails) {
            binding.run {
                txtMovieName.text = movies.title
                txtMovieDuration.text = movies.releaseDate

                Glide.with(itemView)
                    .load("${imageUrl()}${movies.posterPath}")
                    .centerCrop()
                    .into(imgPosterMovie)

                itemView.setOnClickListener {
                    itemClick.invoke(movies)
                }

                itemView.setOnLongClickListener {
                    longClick?.invoke(movies)
                    return@setOnLongClickListener true
                }
            }
        }
    }
}
