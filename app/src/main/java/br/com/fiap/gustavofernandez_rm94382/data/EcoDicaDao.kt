package br.com.fiap.gustavofernandez_rm94382.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.gustavofernandez_rm94382.model.EcoDicaModel

@Dao
interface EcoDicaDao {

    @Query("SELECT * FROM EcoDicaModel")
    fun getAll(): LiveData<List<EcoDicaModel>>

    @Insert
    fun insert(dica: EcoDicaModel)

    @Delete
    fun delete(dica: EcoDicaModel)
}
