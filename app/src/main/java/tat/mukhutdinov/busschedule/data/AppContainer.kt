package tat.mukhutdinov.busschedule.data

import android.content.Context

interface AppContainer {
    val itemsRepository: ScheduleRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val itemsRepository: ScheduleRepository by lazy {
        OfflineScheduleRepository(
            BusScheduleDatabase.getDatabase(context)
                .scheduleDao()
        )
    }
}
