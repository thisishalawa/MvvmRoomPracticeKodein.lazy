package master.mvvm.room_practice

import android.app.Application
import master.mvvm.room_practice.shopping_cart.data.db.ShoppingDatabase
import master.mvvm.room_practice.shopping_cart.repository.ShoppingRepository
import master.mvvm.room_practice.shopping_cart.ui.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {
    /*Implement Kodein
    * bindWithKotlinAny & Singleton
    *  Avoid Instance Object In Main Class
    *
    *
    *
    * */
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { ShoppingDatabase(instance()) }
        bind() from singleton { ShoppingRepository(instance()) }
        bind() from provider { ShoppingViewModelFactory(instance()) }


    }
}