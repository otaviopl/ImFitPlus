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

    // Propriedades da classe (acessíveis em qualquer função)
    private var name: String = ""
    private var sex: String = ""
    private var age: Int = 0
    private var weightKg: Double = 0.0
    private var heightM: Double = 0.0
    private var activityLevel: String = ""
    private var bmr: Double = 0.0
    private var tdee: Double = 0.0
    private var caloricCategory: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateCaloricBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        name = intent.getStringExtra("name").orEmpty()
        sex = intent.getStringExtra("sex").orEmpty()
        age = intent.getIntExtra("age", 0)
        weightKg = intent.getDoubleExtra("weightKg", 0.0)
        heightM = intent.getDoubleExtra("heightM", 0.0)
        activityLevel = intent.getStringExtra("activityLevel").orEmpty()

        // Validação
        if (name.isEmpty() || sex.isEmpty() || age <= 0 || weightKg <= 0.0 || heightM <= 0.0) {
            Toast.makeText(this, "Dados insuficientes para calcular o gasto calórico.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Cálculos
        bmr = calculateBmrReference(sex, weightKg, heightM, age)
        tdee = calculateTdeeFromLevel(bmr, activityLevel)

        caloricCategory = when {
            tdee < 1500 -> "Baixo"
            tdee < 2500 -> "Moderado"
            else -> "Alto"
        }

        binding.tvCaloricResult.text = formatCaloricResult(name, bmr, activityLevel)
        binding.btnIdealWeight.setOnClickListener { handleWeightButtonClick() }
        binding.btnBackCaloric.setOnClickListener { finish() }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Gasto Calórico Diário"
    }

    private fun handleWeightButtonClick() {
        val itWeight = Intent(this, CalculateWeight::class.java).apply {
            putExtra("name", name)
            putExtra("activityLevel", activityLevel)
            putExtra("weightKg", weightKg)
            putExtra("heightM", heightM)
            putExtra("age", age)
            putExtra("sex", sex)
            putExtra("imc", intent.getStringExtra("imc"))
            putExtra("categoria", caloricCategory)
            putExtra("caloric_spent", tdee)
        }
        startActivity(itWeight)
    }
}
