package master.mvvm.room_practice.subscribers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import master.mvvm.room_practice.subscribers.repository.SubscriberRepository
import master.mvvm.room_practice.subscribers.ui.SubscriberViewModel

class SubscriberViewModelFactory(private val repository: SubscriberRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        /*
        * Use For Every ViewModel Factory*/

        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel Class")
    }
}