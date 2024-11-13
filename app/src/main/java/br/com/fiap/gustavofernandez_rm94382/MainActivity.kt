package br.com.fiap.gustavofernandez_rm94382

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.gustavofernandez_rm94382.model.EcoDicaModel
import br.com.fiap.gustavofernandez_rm94382.viewmodel.EcoDicaAdapter
import br.com.fiap.gustavofernandez_rm94382.viewmodel.EcoDicaViewModel
import br.com.fiap.gustavofernandez_rm94382.viewmodel.EcoDicaViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var ecoDicaViewModel: EcoDicaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = EcoDicaAdapter { ecoDica -> }
        recyclerView.adapter = adapter

        val dicasEstaticas = listOf(
            EcoDicaModel(titulo = "Use lâmpadas LED", descricao = "Lâmpadas LED consomem menos energia e têm uma vida útil mais longa em comparação com as lâmpadas tradicionais."),
            EcoDicaModel(titulo = "Reduza o uso de plástico", descricao = "Evite o uso de plásticos descartáveis. Utilize alternativas reutilizáveis como garrafas de vidro ou sacolas de pano."),
            EcoDicaModel(titulo = "Desligue os aparelhos quando não estiverem em uso", descricao = "Desligar aparelhos como TV e computador quando não estão em uso ajuda a economizar energia elétrica."),
            EcoDicaModel(titulo = "Compre produtos locais", descricao = "Ao escolher produtos locais, você ajuda a reduzir a pegada de carbono associada ao transporte de mercadorias."),
        )


        val button = findViewById<Button>(R.id.buttonAdicionar)
        val editTextTitulo = findViewById<EditText>(R.id.editTextTitulo)
        val editTextDescricao = findViewById<EditText>(R.id.editTextDescricao)
        val buttonIntegrantes = findViewById<Button>(R.id.buttonIntegrantes)

        button.setOnClickListener {
            if (editTextTitulo.text.isEmpty() || editTextDescricao.text.isEmpty()) {
                // Exibe um erro se os campos estiverem vazios.
                if (editTextTitulo.text.isEmpty()) {
                    editTextTitulo.error = "Título é obrigatório"
                }
                if (editTextDescricao.text.isEmpty()) {
                    editTextDescricao.error = "Descrição é obrigatória"
                }
                return@setOnClickListener
            }

            // Adiciona a nova dica ao ViewModel e limpa os campos de texto.
            ecoDicaViewModel.addDica(editTextTitulo.text.toString(), editTextDescricao.text.toString())
            editTextTitulo.text.clear()
            editTextDescricao.text.clear()
        }

        buttonIntegrantes.setOnClickListener {
            val intent = Intent(this, IntegrantesActivity::class.java)
            startActivity(intent)
        }


        val ecoDicaViewModelFactory = EcoDicaViewModelFactory(application)
        ecoDicaViewModel = ViewModelProvider(this, ecoDicaViewModelFactory).get(EcoDicaViewModel::class.java)



            ecoDicaViewModel.dicasLiveData.observe(this) { dicasBanco ->
                val todasDicas = dicasEstaticas + dicasBanco
            adapter.updateDicas(todasDicas)
        }
    }
}
