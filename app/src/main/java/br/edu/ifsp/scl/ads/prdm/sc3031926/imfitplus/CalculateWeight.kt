package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateWeightBinding
import kotlin.math.abs
//TODO adicionar botao de voltar
class CalculateWeight : AppCompatActivity() {
    private lateinit var binding: CalculateWeightBinding
    private var name: String = ""
    private var activityLevel: String = ""
    private var weightKg: Double = 0.0
    private var heightM: Double = 0.0
    private var age: Int = 0
    private var sex: String = ""
    private var categoria: String? = null
    private var ideal: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Seu peso ideal"

        name = intent.getStringExtra("name").orEmpty()
        sex = intent.getStringExtra("sex").orEmpty()
        age = intent.getIntExtra("age", 0)
        weightKg = intent.getDoubleExtra("weightKg", 0.0)
        heightM = intent.getDoubleExtra("heightM", 0.0)
        activityLevel = intent.getStringExtra("activityLevel").orEmpty()

        if (heightM <= 0.0) {
            Toast.makeText(this, "Altura inválida para calcular o peso ideal.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        ideal = calculateIdealWeight(heightM)
        val delta = if (weightKg > 0) ideal - weightKg else null

        binding.tvIdealResult.text = buildString {
            appendLine("Olá, $name!")
            appendLine("Altura: ${"%.2f".format(heightM)} m")
            appendLine("Peso ideal (IMC=22): ${"%.2f".format(ideal)} kg")
            if (delta != null) {
                val dir = if (delta > 0) "ganhar" else "perder"
                append("Diferença até o ideal: ${"%.2f".format(abs(delta))} kg para $dir")
            }
        }

        binding.btnBackIdeal.setOnClickListener { finish() }
        binding.btnBackHomeIdeal.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        binding.resumeScreenbtn.setOnClickListener { handleWeightButtonClick() }
    }

    private fun calculateIdealWeight(heightM: Double): Double {
        return 22 * (heightM * heightM)
    }

    private fun handleWeightButtonClick() {
        val itWeight = Intent(this, ResumeActivity::class.java).apply {
            putExtra("name", name)
            putExtra("activityLevel", activityLevel)
            putExtra("weightKg", weightKg)
            putExtra("heightM", heightM)
            putExtra("age", age)
            putExtra("sex", sex)
            putExtra("category", categoria)
            putExtra("caloric_spent", intent.getDoubleExtra("caloric_spent", 0.0))
            putExtra( "imc",intent.getStringExtra("imc").orEmpty())
        }
        startActivity(itWeight)
    }
}
