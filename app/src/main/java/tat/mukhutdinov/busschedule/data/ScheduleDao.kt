package tat.mukhutdinov.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * from schedule WHERE stop_name LIKE :stopName")
    fun getItem(stopName: String): Flow<List<BusSchedule>>

    @Query("SELECT * from schedule ORDER BY arrival_time")
    fun getAllItems(): Flow<List<BusSchedule>>
}