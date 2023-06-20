package com.example.movieapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.model.Movie

class MovieAdapter(private val movies: List<Movie>, private val onMovieClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(view: View, val movieName: TextView, val movieImage: ImageView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        val movieName = view.findViewById<TextView>(R.id.movie_name)
        val movieImage = view.findViewById<ImageView>(R.id.movie_image)
        return MovieViewHolder(view, movieName, movieImage)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.apply {
            movieName.text = movie.title
            Glide.with(movieImage.context)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(movieImage)

            itemView.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("movie_title", movie.title)
                    putString("movie_overview", movie.overview)
                    putString("movie_poster_path", movie.posterPath)
                }
                onMovieClick(movie)
            }
        }
    }
}

