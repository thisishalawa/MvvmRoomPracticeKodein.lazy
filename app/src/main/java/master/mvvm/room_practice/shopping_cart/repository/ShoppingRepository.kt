package master.mvvm.room_practice.shopping_cart.repository

import master.mvvm.room_practice.shopping_cart.data.ShoppingItem
import master.mvvm.room_practice.shopping_cart.data.db.ShoppingDatabase


//4
class ShoppingRepository(private val db: ShoppingDatabase) {
    /*implement database methods to provide these methods
    * to view Model so it can call them from repository
    **/

    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}