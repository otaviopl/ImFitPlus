package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager // <--- IMPORTANTE
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.HistoryScreenBinding
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.db.dao.UserDao
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.adapter.HistoryAdapter

class UserHistoryAcvity : AppCompatActivity() {
    private lateinit var binding: HistoryScreenBinding
    private lateinit var userDao: UserDao
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()

        // 1. INICIALIZAR O BANCO DE DADOS (Faltava isso)
        userDao = UserDao(this)

        // 2. CONFIGURAR O LAYOUT MANAGER (Diz que a lista é vertical)
        // Sem isso, o RecyclerView não desenha nada!
        binding.rvHistory.layoutManager = LinearLayoutManager(this)

        binding.btnBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    // 3. CARREGAR OS DADOS QUANDO A TELA APARECE
    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun updateList() {
        // A. Busca a lista do banco
        val list = userDao.getAllUsers()

        // B. Cria o adapter passando a lista
        adapter = HistoryAdapter(list)

        // C. LIGA O ADAPTER NA TELA (Isso resolve o erro do Logcat)
        binding.rvHistory.adapter = adapter
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Histórico de Usuários"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Botão voltar na barra superior
    }

    // Faz o botão voltar da Toolbar funcionar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}