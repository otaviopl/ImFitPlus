package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateCaloricBinding

class CalculateCaloric: AppCompatActivity() {
    private lateinit var binding: CalculateCaloricBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateCaloricBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
}

    private fun setupToolbar() {
        supportActionBar?.title = "Calculadora de Gasto Calórico"
        setSupportActionBar(binding.toolbar.toolbar)
    }
    }
