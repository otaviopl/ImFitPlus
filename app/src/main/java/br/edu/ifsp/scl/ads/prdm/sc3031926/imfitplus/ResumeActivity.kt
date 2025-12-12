package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.db.dao.UserDao
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.ResumeScreenBinding
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.model.User

class ResumeActivity : AppCompatActivity() {
    private lateinit var binding: ResumeScreenBinding

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        binding = ResumeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        val name = intent.getStringExtra("name") ?: ""
        val age = intent.getIntExtra("age", 0)
        val weight = intent.getDoubleExtra("weightKg", 0.0)
        val height = intent.getDoubleExtra("heightM", 0.0)
        val gender = intent.getStringExtra("gender") ?: ""
        val activityLevel = intent.getStringExtra("activityLevel") ?: ""
        val imc = intent.getStringExtra("imc") ?: ""
        val imcCategory = intent.getStringExtra("category") ?: ""
        val caloricSpent = intent.getDoubleExtra("caloric_spent", 0.0)
        val idealWeight = intent.getStringExtra("idealWeight") ?: ""
        val waterNeeded = calculateWaterNeeded(weight)
        val formatedIdealWeight = "%.2f".format(idealWeight.toDoubleOrNull() ?: 0.0)
        val formatedCalories = "%.2f".format(caloricSpent)
        val ageLocalDate =  intent.getStringExtra("ageLocalDate").orEmpty()
        val maxCardiacTax = calculateMaxCardiac(age)
        val sixtyTax =  1.6 * maxCardiacTax
        val fiftyTax = 1.5 * maxCardiacTax
        val seventyTax = 1.7 * maxCardiacTax
        val eightyTax = 1.8 * maxCardiacTax
        val ninety =  1.9 * maxCardiacTax

        // Exibindo o resumo na tela
        binding.resultText.text =
            "Nome: $name\nIMC: $imc\nNível de Atividade: $activityLevel\nCalorias gastas diariamente:$formatedCalories\nQuantidade de aguá recomedada: $waterNeeded L\n Categoria do seu IMC: $imcCategory\nFrequencia cardiaca maxima:$maxCardiacTax\nZona leve de treino vai de $fiftyTax até $sixtyTax batimentos cardiacos\nA queima de gordura vai começar em $sixtyTax até $seventyTax batimentos cardiacos\nZona aeróbica vai de $seventyTax até $eightyTax batimentos cardiacos\nSua zona anaerobica vai de $eightyTax até $ninety batimentos cardiacos"
        val user = User(
            name = name,
            bithdayDate = ageLocalDate,
            maxCardiac = maxCardiacTax,
            age = age,
            weight = weight,
            height = height,
            gender = gender,
            sportsLevel = activityLevel,
            imc = imc,
            imcCategory = imcCategory,
            baseCalories = formatedCalories.toString(),
            idealWeight = formatedIdealWeight,
            waterConsumption = waterNeeded.toString()
        )

        val userDao = UserDao(this)
        userDao.insert(user)

        binding.btnBack.setOnClickListener { finish() }
        binding.btnHistory.setOnClickListener { handleHistoryButtonClick() }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Resumo da sua saúde!"
    }

    private fun calculateWaterNeeded(weight: Double?): Double {
        if (weight == null) return 0.0
        val result = 0.035 * weight
        return String.format("%.2f", result).toDouble()
    }

    private fun calculateMaxCardiac(age:Int):Int{
        return 220 - age
    }

    private fun handleHistoryButtonClick() {
        val intent = Intent(this, UserHistoryAcvity::class.java)
        startActivity(intent)
    }
}
