package com.example.movieapplication.ui.mainFragment

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.adapter.MovieAdapter
import com.example.movieapplication.base.BaseFragment
import com.example.movieapplication.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>(
    layoutId = R.layout.fragment_main,
    viewModelClass = MainFragmentViewModel::class.java
) {
    private lateinit var moviesAdapter: MovieAdapter

    override fun onInitDataBinding() {
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        moviesAdapter = MovieAdapter(mutableListOf()) { movie ->
            // handle movie click event
            val bundle = Bundle().apply {
                putString("movie_title", movie.title)
                putString("movie_overview", movie.overview)
                putString("movie_poster_path", movie.posterPath)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = moviesAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (totalItemCount <= (lastVisibleItem + 1)) {
                        viewModel.loadNextPage()
                    }
                }
            })
        }
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                moviesAdapter.addMovies(it)
            }
        }
    }
}

