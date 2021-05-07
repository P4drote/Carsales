package com.example.carsalesprueba.ui.main

import androidx.lifecycle.*
import com.example.carsalesprueba.data.RemoteRepository
import com.example.carsalesprueba.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RemoteRepository): ViewModel() {

    private val date = MutableLiveData<String>()

    fun setDate(newDate: String) {
        date.value = newDate
    }

    val getStatistic = date.distinctUntilChanged().switchMap {
        liveData (Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(repository.getCoronavirusInformation(it))
            }catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }
}
