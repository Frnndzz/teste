package br.com.fiap.gustavofernandez_rm94382.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import br.com.fiap.gustavofernandez_rm94382.data.EcoDicaDao
import br.com.fiap.gustavofernandez_rm94382.data.EcoDicaDatabase
import br.com.fiap.gustavofernandez_rm94382.model.EcoDicaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EcoDicaViewModel(application: Application) : AndroidViewModel(application) {

    private val ecoDicaDao: EcoDicaDao


    val dicasLiveData: LiveData<List<EcoDicaModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            EcoDicaDatabase::class.java,
            "eco_dica_database"
        ).build()

        ecoDicaDao = database.ecoDicaDao()

        dicasLiveData = ecoDicaDao.getAll()
    }

    fun addDica(titulo: String, descricao: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Cria um novo EcoDicaModel com os dados fornecidos.
            val novaDica = EcoDicaModel(titulo = titulo, descricao = descricao)
            // Insere a nova dica no banco de dados.
            ecoDicaDao.insert(novaDica)
        }
    }

    fun removeDica(dica: EcoDicaModel) {
        viewModelScope.launch(Dispatchers.IO) {
            // Remove a dica do banco de dados.
            ecoDicaDao.delete(dica)
        }
    }
}

