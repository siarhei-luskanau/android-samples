package siarhei.luskanau.example.room_rxjava.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
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