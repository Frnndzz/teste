package br.com.fiap.gustavofernandez_rm94382.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EcoDicaModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val titulo: String,
    val descricao: String
)

