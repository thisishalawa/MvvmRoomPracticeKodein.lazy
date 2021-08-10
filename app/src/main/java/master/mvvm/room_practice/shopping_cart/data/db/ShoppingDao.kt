package master.mvvm.room_practice.shopping_cart.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import master.mvvm.room_practice.shopping_cart.data.ShoppingItem

//2
@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}