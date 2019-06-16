package siarhei.luskanau.example.roomrxjava.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        ModelEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun modelDao(): ModelDao

    companion object {
        @JvmField
        val MIGRATIONS = arrayOf<Migration>(
            object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    dropOtherColumns(
                        database,
                        "models",
                        mapOf(
                            "`row_id`" to "INTEGER NOT NULL",
                            "`name`" to "TEXT",
                            "PRIMARY KEY(`row_id`)" to null
                        )
                    )
                }
            }
        )

        private fun dropOtherColumns(
            database: SupportSQLiteDatabase,
            tableName: String,
            newColumns: Map<String, String?>
        ) {
            val createTableColumns = newColumns
                .toList()
                .joinToString(prefix = "\n", separator = ",\n", postfix = "\n") { (key, value) ->
                    "$key ${value.orEmpty()}"
                }

            val selectColumns = newColumns
                .filterValues { it != null }
                .keys
                .joinToString(separator = ", ")

            database.execSQL("CREATE TABLE ${tableName}_backup($createTableColumns)")
            database.execSQL("INSERT INTO ${tableName}_backup SELECT $selectColumns FROM $tableName")
            database.execSQL("DROP TABLE $tableName")
            database.execSQL("ALTER TABLE ${tableName}_backup RENAME TO $tableName")
        }
    }
}
