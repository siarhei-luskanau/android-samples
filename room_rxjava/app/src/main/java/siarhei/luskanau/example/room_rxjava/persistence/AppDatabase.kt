package siarhei.luskanau.example.room_rxjava.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ModelEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun modelDao(): ModelDao
}
