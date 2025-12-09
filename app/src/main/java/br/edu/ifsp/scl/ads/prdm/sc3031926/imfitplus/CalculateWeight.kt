package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateWeightBinding
import kotlin.math.abs

class CalculateWeight : AppCompatActivity() {
    private lateinit var binding: CalculateWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Seu peso ideal"

        val name     = intent.getStringExtra("name").orEmpty()
        val heightM  = intent.getDoubleExtra("heightM", 0.0)
        val weightKg = intent.getDoubleExtra("weightKg", 0.0)
        val imc = intent.getStringExtra("imc")
        // val caloric_spent = intent.getDoubleExtra("caloric_spent")

        if (heightM <= 0.0) {
            Toast.makeText(this, "Altura inválida para calcular o peso ideal.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val ideal = calculateIdealWeight(heightM)
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
        binding.resumeScreenbtn.setOnClickListener { handleResumeScreenButtonClick() }

    }

    private fun calculateIdealWeight(heightM: Double): Double {
        return 22 * (heightM * heightM)
    }
    private fun handleResumeScreenButtonClick() {
        val itResume = Intent(this, ResumeActivity::class.java).apply {
            putExtra("name", intent.getStringExtra("name"))
            putExtra("activityLevel", intent.getStringExtra("activityLevel"))
            putExtra("weightKg", intent.getDoubleExtra("weightKg", 0.0))
            putExtra("heightM", intent.getDoubleExtra("heightM", 0.0))
            putExtra("age", intent.getIntExtra("age", 0))
            putExtra("sex", intent.getStringExtra("sex"))
            putExtra("imc", intent.getStringExtra("imc"))
            putExtra("category", intent.getStringExtra("category"))
            putExtra("idealWeight", intent.getStringExtra("idealWeight"))
            putExtra("caloric_spent", intent.getStringExtra("caloric_spent"))


        }
        startActivity(itResume)
    }
}
