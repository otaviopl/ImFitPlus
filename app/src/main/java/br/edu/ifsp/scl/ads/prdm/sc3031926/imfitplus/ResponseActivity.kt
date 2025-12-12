package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.ResultActivityBinding

class ResponseActivity : AppCompatActivity() {
    private lateinit var binding: ResultActivityBinding
    private var category: String? = ""
    private var imc: String? = ""
    private var name: String? = ""
    private var activityLevel: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        name = intent.getStringExtra("name")
        imc = intent.getStringExtra("imc")
        category = intent.getStringExtra("categoria")
        activityLevel = intent.getStringExtra("activityLevel")
        binding.resultText.text = "Nome: $name\nIMC: $imc\nCategoria: $category\nNível de Atividade: $activityLevel"
        binding.btnBack.setOnClickListener { finish() }
        binding.btnBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        binding.btnCaloric.setOnClickListener { handleCaloricButtonClick() }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Resultado IMC"
    }

    private fun handleCaloricButtonClick() {
        val itCal = Intent(this, CalculateCaloricActivity::class.java).apply {
            putExtra("name", name)
            putExtra("activityLevel", activityLevel)
            putExtra("weightKg", intent.getDoubleExtra("weightKg", 0.0))
            putExtra("heightM", intent.getDoubleExtra("heightM", 0.0))
            putExtra("age", intent.getIntExtra("age", 0))
            putExtra("sex", intent.getStringExtra("sex"))
            putExtra("ageLocalDate", intent.getStringExtra("ageLocalDate"))
            putExtra("imc", imc)
            putExtra("category", category)
        }
        startActivity(itCal)
    }
}