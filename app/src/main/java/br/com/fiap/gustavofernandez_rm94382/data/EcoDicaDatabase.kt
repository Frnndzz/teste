package br.com.fiap.gustavofernandez_rm94382.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.gustavofernandez_rm94382.model.EcoDicaModel



@Database(entities = [EcoDicaModel::class], version = 1)
abstract class EcoDicaDatabase : RoomDatabase() {

    abstract fun ecoDicaDao(): EcoDicaDao

}
