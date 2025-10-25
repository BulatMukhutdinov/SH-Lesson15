package tat.mukhutdinov.busschedule.data

import kotlinx.coroutines.flow.Flow


interface ScheduleRepository {

    fun getAllItemsStream(): Flow<List<BusSchedule>>

    fun getItemStream(stopName: String): Flow<List<BusSchedule>>
}
