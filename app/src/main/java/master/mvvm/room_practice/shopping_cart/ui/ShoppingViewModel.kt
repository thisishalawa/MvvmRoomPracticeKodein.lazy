package master.mvvm.room_practice.shopping_cart.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import master.mvvm.room_practice.shopping_cart.data.ShoppingItem
import master.mvvm.room_practice.shopping_cart.repository.ShoppingRepository

//5
class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {
    //call repository methods

    /* Coroutines getting Started Calling suspend fun
    **/
    fun upsert(item: ShoppingItem) = GlobalScope.launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}