package master.mvvm.room_practice.shopping_cart.dialog

import master.mvvm.room_practice.shopping_cart.data.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}