package br.com.fiap.gustavofernandez_rm94382.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.gustavofernandez_rm94382.R
import br.com.fiap.gustavofernandez_rm94382.model.EcoDicaModel

class EcoDicaAdapter(private val onItemRemoved: (EcoDicaModel) -> Unit) :
    RecyclerView.Adapter<EcoDicaAdapter.EcoDicaViewHolder>() {

    private var dicas = listOf<EcoDicaModel>()

    inner class EcoDicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloTextView: TextView = view.findViewById(R.id.textViewTitulo)
        val descricaoTextView: TextView = view.findViewById(R.id.textViewDescricao)

        fun bind(dica: EcoDicaModel) {
            tituloTextView.text = dica.titulo
            descricaoTextView.text = dica.descricao
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EcoDicaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dica_layout, parent, false)
        return EcoDicaViewHolder(view)
    }

    override fun getItemCount(): Int = dicas.size

    override fun onBindViewHolder(holder: EcoDicaViewHolder, position: Int) {
        val dica = dicas[position]
        holder.bind(dica)
    }

    fun updateDicas(newDicas: List<EcoDicaModel>) {
        dicas = newDicas
        notifyDataSetChanged()
    }
}


