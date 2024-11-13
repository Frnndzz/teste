package br.com.fiap.gustavofernandez_rm94382

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IntegrantesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integrantes)

        val textViewNome1 = findViewById<TextView>(R.id.textViewNome1)
        val textViewRM1 = findViewById<TextView>(R.id.textViewRM1)
        val textViewNome2 = findViewById<TextView>(R.id.textViewNome2)
        val textViewRM2 = findViewById<TextView>(R.id.textViewRM2)


        textViewNome1.text = "Gustavo Fernandez Fontes"
        textViewRM1.text = "RM: 94382"
        textViewNome2.text = "Vinicius da Silva Pires"
        textViewRM2.text = "RM: 96187"
    }
}