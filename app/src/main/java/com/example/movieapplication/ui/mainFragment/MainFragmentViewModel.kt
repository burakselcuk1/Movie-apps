package com.example.movieapplication.ui.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.base.BaseViewModel
import com.example.movieapplication.model.Movie
import com.example.movieapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repository: MovieRepository) : BaseViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private var currentPage = 1

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            val result = repository.getPopularMovies(currentPage)
            result.fold(
                onSuccess = { movieResponse ->
                    val newMovies = movieResponse.movies
                    val allMovies = _movies.value.orEmpty().toMutableList()
                    allMovies.addAll(newMovies)
                    _movies.value = allMovies
                },
                onFailure = { exception ->
                    // handle error
                }
            )
        }
    }

    fun loadNextPage() {
        currentPage++
        getPopularMovies()
    }
}
