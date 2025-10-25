package tat.mukhutdinov.inventory.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {

    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase

    private var item1 = Item(id = 1, name = "Apples", price = 10.0, quantity = 20)
    private var item2 = Item(id = 2, name = "Bananas", price = 15.0, quantity = 97)

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        inventoryDatabase.close()
    }

    @Test
    fun insertsItemIntoDB() = runTest {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
    }

    @Test
    fun daoGetAllItems_returnsAllItemsFromDB() = runTest {
        addTwoItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
        assertEquals(allItems[1], item2)
    }

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }
}