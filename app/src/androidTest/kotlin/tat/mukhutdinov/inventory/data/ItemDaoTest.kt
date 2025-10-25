package tat.mukhutdinov.inventory.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
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
        addTwoItemsToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
        assertEquals(allItems[1], item2)
    }

    @Test
    fun daoUpdateItems_updatesItemsInDB() = runTest {
        val updatedItem1 = item1.copy(price = 15.0)
        val updatedItem2 = item2.copy(quantity = 0)
        addTwoItemsToDb()

        itemDao.update(updatedItem1)
        itemDao.update(updatedItem2)

        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], updatedItem1)
        assertEquals(allItems[1], updatedItem2)
    }

    @Test
    fun daoDeleteItems_deletesAllItemsFromDB() = runTest {
        addTwoItemsToDb()
        itemDao.delete(item1)
        itemDao.delete(item2)

        val allItems = itemDao.getAllItems().first()

        assertTrue(allItems.isEmpty())
    }

    @Test
    fun daoGetItem_returnsItemFromDB() = runTest {
        addOneItemToDb()

        val item = itemDao.getItem(1)

        assertEquals(item.first(), item1)
    }

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }
}