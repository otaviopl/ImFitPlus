package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar(binding)
        binding.buttonStart.setOnClickListener{
            Intent("CALCULATE_IMC").let {this.startActivity(it)}
        }
    }

    private fun setupToolbar(binding: MainActivityBinding) {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Seja bem vindo!"
    }

}