package tat.mukhutdinov.inventory.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: ItemsRepository by lazy {
        val database: InventoryDatabase = InventoryDatabase.getDatabase(context)
        val itemDao: ItemDao = database.itemDao()
        OfflineItemsRepository(itemDao)
    }
}
