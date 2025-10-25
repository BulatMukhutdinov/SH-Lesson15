package tat.mukhutdinov.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import tat.mukhutdinov.busschedule.BusScheduleApplication
import tat.mukhutdinov.busschedule.data.BusSchedule
import tat.mukhutdinov.busschedule.data.ScheduleRepository

class BusScheduleViewModel(
    private val repository: ScheduleRepository
) : ViewModel() {

    fun getFullSchedule(): Flow<List<BusSchedule>> =
        repository.getAllItemsStream()

    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> =
        repository.getItemStream(stopName)

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication)
                BusScheduleViewModel(app.container.itemsRepository)
            }
        }
    }
}
