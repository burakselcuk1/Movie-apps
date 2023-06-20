package com.example.movieapplication.ui.mainFragment

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.adapter.MovieAdapter
import com.example.movieapplication.base.BaseFragment
import com.example.movieapplication.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>(
    layoutId = R.layout.fragment_main,
    viewModelClass = MainFragmentViewModel::class.java
) {
    override fun onInitDataBinding() {

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun setupObservers() {
        viewModel.getPopularMovies().observe(viewLifecycleOwner) { movieResponse ->
            movieResponse?.let {
                binding.recyclerView.adapter = MovieAdapter(it.movies) { movie ->
                    // handle movie click event
                    val bundle = Bundle().apply {
                        putString("movie_title", movie.title)
                        putString("movie_overview", movie.overview)
                        putString("movie_poster_path", movie.posterPath)
                    }
                    findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
                }
            } ?: run {
                // handle error
            }
        }
    }
}
