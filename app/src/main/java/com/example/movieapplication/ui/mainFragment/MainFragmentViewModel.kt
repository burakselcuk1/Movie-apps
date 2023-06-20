package com.example.movieapplication.ui.mainFragment

import androidx.lifecycle.liveData
import com.example.movieapplication.base.BaseViewModel
import com.example.movieapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repository: MovieRepository): BaseViewModel() {

    fun getPopularMovies() = liveData(Dispatchers.IO) {
        try {
            emit(repository.getPopularMovies())
        } catch (exception: Exception) {
            // handle the error
        }
    }
}