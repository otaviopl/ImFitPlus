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
        val activityLevel = intent.getStringExtra("activityLevel")
        val caloricSpent = intent.getDoubleExtra("caloric_spent",0.0)
        val weight =  intent.getDoubleExtra("weightKg",0.0)
        val waterNeeded = calculateWaterNeeded(weight)

        binding.resultText.text =
            "Nome: $name\nIMC: $imc\nNível de Atividade: $activityLevel\nCalorias gastas diaramente:$caloricSpent\nQuantidade de aguá recomedada: $waterNeeded L\n"
        binding.btnBack.setOnClickListener { finish() }
    }
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Resumo da sua saúde!"
    }

    fun calculateWaterNeeded(weight: Double?): Double {
        if (weight == null) return 0.0

        val result = 0.035 * weight
        return String.format("%.2f", result).toDouble()
    }



}

