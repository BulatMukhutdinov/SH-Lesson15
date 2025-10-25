package tat.mukhutdinov.inventory

import android.app.Application
import tat.mukhutdinov.inventory.data.AppContainer
import tat.mukhutdinov.inventory.data.AppDataContainer

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
