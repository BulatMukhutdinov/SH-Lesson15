package tat.mukhutdinov.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(
    private val itemDao: ScheduleDao
) : ScheduleRepository {

    override fun getAllItemsStream(): Flow<List<BusSchedule>> =
        itemDao.getAllItems()

    override fun getItemStream(stopName: String): Flow<List<BusSchedule>> =
        itemDao.getItem(stopName)

}
