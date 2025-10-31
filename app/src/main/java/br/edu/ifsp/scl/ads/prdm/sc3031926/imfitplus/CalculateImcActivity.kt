package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateImcBinding

class CalculateImcActivity: AppCompatActivity() {
    private lateinit var binding: CalculateImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateImcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar(binding)
    }

    private fun setupToolbar(binding: CalculateImcBinding) {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Calculadora de IMC"
    }


}