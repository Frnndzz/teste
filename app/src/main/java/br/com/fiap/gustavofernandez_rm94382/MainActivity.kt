package br.com.fiap.gustavofernandez_rm94382

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.gustavofernandez_rm94382.viewmodel.EcoDicaAdapter
import br.com.fiap.gustavofernandez_rm94382.viewmodel.EcoDicaViewModel
import br.com.fiap.gustavofernandez_rm94382.viewmodel.EcoDicaViewModelFactory

class MainActivity : AppCompatActivity() {

    // O ViewModel usado para gerenciar as dicas.
    private lateinit var ecoDicaViewModel: EcoDicaViewModel

    /**
     * Chamado quando a activity é criada.
     * Este método configura a interface do usuário e inicializa o ViewModel.
     *
     * @param savedInstanceState Se a activity está sendo recriada a partir de um estado salvo, este é o estado.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Define o layout da activity.
        setContentView(R.layout.activity_main)

        // Encontra a barra de ferramentas e configura como a barra de ação.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        // Encontra o RecyclerView pelo seu ID.
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        // Cria o adaptador para o RecyclerView.
        val adapter = EcoDicaAdapter { ecoDica ->
            // Quando uma dica é clicada, exibe um Toast com a descrição.
            Toast.makeText(this, ecoDica.descricao, Toast.LENGTH_LONG).show()
        }
        recyclerView.adapter = adapter

        // Encontra o botão e os campos de texto pelo seus IDs.
        val button = findViewById<Button>(R.id.buttonAdicionar)
        val editTextTitulo = findViewById<EditText>(R.id.editTextTitulo)
        val editTextDescricao = findViewById<EditText>(R.id.editTextDescricao)

        // Define o que acontece quando o botão é clicado.
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

        // Cria a fábrica para o ViewModel.
        val ecoDicaViewModelFactory = EcoDicaViewModelFactory(application)
        // Obtém a instância do ViewModel.
        ecoDicaViewModel = ViewModelProvider(this, ecoDicaViewModelFactory).get(EcoDicaViewModel::class.java)

        // Observa as mudanças na lista de dicas e atualiza o adaptador quando a lista muda.
        ecoDicaViewModel.dicasLiveData.observe(this) { dicas ->
            adapter.updateDicas(dicas)
        }
    }
}
