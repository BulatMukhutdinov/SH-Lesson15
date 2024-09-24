package tat.mukhutdinov.lesson17

import android.app.Application
import tat.mukhutdinov.lesson17.data.AppContainer
import tat.mukhutdinov.lesson17.data.AppDataContainer

class InventoryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
