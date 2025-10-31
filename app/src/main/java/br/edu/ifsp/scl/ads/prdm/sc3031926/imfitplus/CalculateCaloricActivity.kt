package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateCaloricBinding
import kotlin.math.roundToInt

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
        val weightKg      = intent.getDoubleExtra("weightKg", 0.0)              // <- chave correta
        val heightM       = intent.getDoubleExtra("heightM", 0.0)               // <- vem em metros         // <- converte para cm
        val activityLevel = intent.getStringExtra("activityLevel").orEmpty()

        if (name.isEmpty() || sex.isEmpty() || age <= 0 || weightKg <= 0.0 || heightM <= 0.0) {
            Toast.makeText(this, "Dados insuficientes para calcular o gasto calórico.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val bmr = calculateBmrReference(sex, weightKg, heightM, age)
        val factor = activityFactor(activityLevel)
        val tdee  = bmr * factor

        binding.tvCaloricResult.text = """
            Olá, $name!
            TMB (Taxa Metabólica Basal): ${bmr.roundToInt()} kcal/dia
            Fator de atividade ($activityLevel): $factor
            Gasto Calórico Diário (TDEE): ${tdee.roundToInt()} kcal/dia
        """.trimIndent()

        binding.btnIdealWeight.setOnClickListener { handleWeightButtonClick(bmr, tdee) }
        binding.btnBackCaloric.setOnClickListener { finish() }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Gasto Calórico Diário"
    }

    private fun calculateBmrReference(
        sex: String,
        weightKg: Double,
        heightM: Double,
        age: Int
    ): Double {
        return if (sex.equals("Masculino", ignoreCase = true)) {
            66 + (13.7 * weightKg) + (5 * heightM * 100) - (6.8 * age)
        } else {
            655 + (9.6 * weightKg) + (1.8 * heightM * 100) - (4.7 * age)
        }
    }

    private fun activityFactor(level: String): Double {
        val lv = level.lowercase().replace("á","a")
        return when (lv) {
            "sedentario" -> 1.2
            "leve"       -> 1.375
            "moderado"   -> 1.55
            "intenso"    -> 1.725
            else         -> 1.2
        }
    }

    private fun handleWeightButtonClick(caloric_category: Double, caloric_spent:Double) {
        val itWeight = Intent(this, CalculateWeight::class.java).apply {
            putExtra("name", intent.getStringExtra("name"))
            putExtra("activityLevel", intent.getStringExtra("activityLevel"))
            putExtra("weightKg", intent.getDoubleExtra("weightKg", 0.0))
            putExtra("heightM", intent.getDoubleExtra("heightM", 0.0))
            putExtra("age", intent.getIntExtra("age", 0))
            putExtra("sex", intent.getStringExtra("sex"))
            putExtra("caloric_category", caloric_category )
            putExtra("caloric_spent", caloric_spent )

        }
        startActivity(itWeight)
    }
}
