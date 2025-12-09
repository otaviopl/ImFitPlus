package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.ResumeScreenBinding

class ResumeActivity: AppCompatActivity() {
    private lateinit var binding: ResumeScreenBinding
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        binding = ResumeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        val name = intent.getStringExtra("name")
        val imc = intent.getStringExtra("imc")
        val categoria = intent.getStringExtra("categoria")
        val activityLevel = intent.getStringExtra("activityLevel")
        val caloric_spent = intent.getStringExtra("caloric_spent")
        val water_needed =  intent.getStringExtra("water_nedeed")
        binding.resultText.text =
            "Nome: $name\nIMC: $imc\nCategoria: $categoria\nNível de Atividade: $activityLevel\nCalorias gastas diaramente:$caloric_spent\nQuantidade de aguá recomedada: $water_needed"
    }
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Resumo da sua saúde!"
    }

}

