package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.HistoryScreenBinding
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.db.dao.UserDao
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.adapter.HistoryAdapter


class UserHistoryAcvity : AppCompatActivity() {
    private lateinit var binding: HistoryScreenBinding
    private lateinit var rwHistory: RecyclerView
    private lateinit var userDao: UserDao
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        binding.btnBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }    }
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Histórico de Usuários"
    }
}