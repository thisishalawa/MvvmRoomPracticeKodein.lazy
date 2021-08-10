package master.mvvm.room_practice.shopping_cart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import master.mvvm.room_practice.shopping_cart.repository.ShoppingRepository

@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(
    private val repository: ShoppingRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T // generic type
    }
}