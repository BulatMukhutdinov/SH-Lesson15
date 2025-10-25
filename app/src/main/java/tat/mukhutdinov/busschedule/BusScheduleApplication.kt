package tat.mukhutdinov.busschedule

import android.app.Application
import tat.mukhutdinov.busschedule.data.AppContainer
import tat.mukhutdinov.busschedule.data.AppDataContainer

class BusScheduleApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
