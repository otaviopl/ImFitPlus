package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.ResultActivityBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ResultActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        val name = intent.getStringExtra("name")
        val imc = intent.getStringExtra("imc")
        val categoria = intent.getStringExtra("categoria")
        binding.resultText.text = "Nome: $name\nIMC: $imc\nCategoria: $categoria"
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Resultado IMC"
    }
}
