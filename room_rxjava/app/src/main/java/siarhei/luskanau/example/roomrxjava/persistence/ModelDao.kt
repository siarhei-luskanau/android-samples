package siarhei.luskanau.example.roomrxjava.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Flowable

@Dao
interface ModelDao {

    @Query("SELECT * FROM models ORDER BY name DESC")
    fun getModels(): Flowable<List<ModelEntity>>

    @Insert
    fun insertModel(model: ModelEntity)

    @Update
    fun updateModel(model: ModelEntity)

    @Query("DELETE FROM models")
    fun deleteAllModels()
}
