package com.example.movieapplication.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.base.BaseFragment
import com.example.movieapplication.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>(
    layoutId = R.layout.fragment_detail,
    viewModelClass = DetailFragmentViewModel::class.java
) {
    override fun onInitDataBinding() {
        arguments?.let {
            val title = it.getString("movie_title")
            val overview = it.getString("movie_overview")
            val posterPath = it.getString("movie_poster_path")

            binding.movieTitle.text = title
            binding.movieOverview.text = overview
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500$posterPath")
                .into(binding.movieImage)
        }
    }
}
