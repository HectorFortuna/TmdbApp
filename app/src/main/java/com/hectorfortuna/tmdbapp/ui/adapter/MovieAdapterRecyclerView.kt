package com.hectorfortuna.tmdbapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import com.hectorfortuna.tmdbapp.databinding.MovieItemBinding
import com.hectorfortuna.tmdbapp.util.imageUrl

class MovieAdapterRecyclerView(
    private val movieResults: List<Result>,
    private val itemClick: ((item: Result) -> Unit),
    private val loadScreen: (() -> Unit)? = null
) : RecyclerView.Adapter<MovieAdapterRecyclerView.MovieTestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTestViewHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieTestViewHolder(binding,itemClick)
    }

    override fun getItemCount() = movieResults.count()

    override fun onBindViewHolder(holder: MovieTestViewHolder, position: Int) {
        holder.bindView(movieResults[position])
        if(movieResults.count()-1 == position){
            loadScreen?.invoke()
        }
    }

    class MovieTestViewHolder(
        private val binding: MovieItemBinding,
        private val itemClick: ((item: Result) -> Unit),
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(moviesResult: Result) {
            binding.run {
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

    }
}
