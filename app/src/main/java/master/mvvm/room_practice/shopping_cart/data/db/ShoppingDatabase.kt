package master.mvvm.room_practice.shopping_cart.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import master.mvvm.room_practice.shopping_cart.data.ShoppingItem

//3
@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {
    // inherit from roomDatabase

    /* abstract fun return ShoppingDao interface to make sure we can
    * access to database operation from here! database class
    * */
    abstract fun getShoppingDao(): ShoppingDao


    /*static in java
    * instance of shoppingDatabase {only one instance in the time} == instance
    *
    * */
    companion object {
        @Volatile
        /*visible to other thread -- only one thread in reading that instance*/
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        /*this fun will executed whenever we create an instance
        + if instance null create one
        **/
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }


        /*create a  room database*/
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java, "RoomPractice_ShoppingDB.db"
            ).build()
    }
}