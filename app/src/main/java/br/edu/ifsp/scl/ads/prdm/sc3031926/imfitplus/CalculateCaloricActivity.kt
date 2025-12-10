package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateCaloricBinding
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.CaloricUtils.calculateTdeeFromLevel
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.CaloricUtils.calculateBmrReference
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.CaloricUtils.formatCaloricResult

class CalculateCaloricActivity : AppCompatActivity() {
    private lateinit var binding: CalculateCaloricBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateCaloricBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        val name          = intent.getStringExtra("name").orEmpty()
        val sex           = intent.getStringExtra("sex").orEmpty()
        val age           = intent.getIntExtra("age", 0)
        val weightKg      = intent.getDoubleExtra("weightKg", 0.0)
        val heightM       = intent.getDoubleExtra("heightM", 0.0) // metros
        val activityLevel = intent.getStringExtra("activityLevel").orEmpty()

        if (name.isEmpty() || sex.isEmpty() || age <= 0 || weightKg <= 0.0 || heightM <= 0.0) {
            Toast.makeText(this, "Dados insuficientes para calcular o gasto calórico.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val bmr = calculateBmrReference(sex, weightKg, heightM, age)
        val tdee = calculateTdeeFromLevel(bmr, activityLevel)

        binding.tvCaloricResult.text = formatCaloricResult(name, bmr, activityLevel, tdee)
        binding.btnIdealWeight.setOnClickListener { handleWeightButtonClick(bmr, tdee) }
        binding.btnBackCaloric.setOnClickListener { finish() }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Gasto Calórico Diário"
    }

    private fun handleWeightButtonClick(caloric_category: Double, caloric_spent: Double) {
        val itWeight = Intent(this, CalculateWeight::class.java).apply {
            putExtra("name", intent.getStringExtra("name"))
            putExtra("activityLevel", intent.getStringExtra("activityLevel"))
            putExtra("weightKg", intent.getDoubleExtra("weightKg", 0.0))
            putExtra("heightM", intent.getDoubleExtra("heightM", 0.0))
            putExtra("age", intent.getIntExtra("age", 0))
            putExtra("sex", intent.getStringExtra("sex"))
            putExtra("caloric_category", caloric_category)
            putExtra("caloric_spent", intent.getDoubleExtra("caloric_spent",caloric_spent))
        }
        startActivity(itWeight)
    }
}
