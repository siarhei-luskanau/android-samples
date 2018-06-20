package siarhei.luskanau.example.room_rxjava.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "models")
data class ModelEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "row_id")
        val rowId: Int? = null,

        @ColumnInfo(name = "name")
        val name: String

)