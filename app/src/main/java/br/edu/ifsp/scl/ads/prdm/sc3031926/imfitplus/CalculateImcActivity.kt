package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateImcBinding

class CalculateImcActivity : AppCompatActivity() {
    private lateinit var binding: CalculateImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding)
        setupSpinner()
        binding.calcImcbtn.setOnClickListener { handleImcButtonClick() }
        binding.btnBack.setOnClickListener { finish() }

    }

    private fun setupToolbar(binding: CalculateImcBinding) {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Calculadora de IMC"
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.activity_levels,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerActivity.adapter = adapter
    }

    private fun handleImcButtonClick() {
        val name = binding.inputName.text.toString().trim()
        val weightStr = binding.inputWeight.text.toString().trim()
        val heightStr = binding.inputHeight.text.toString().trim()
        val ageInt = binding.inputAge.text.toString().trim().toIntOrNull()

        if (ageInt == null) {
            Toast.makeText(this, "Informe uma idade válida!", Toast.LENGTH_SHORT).show()
            return
        }

        if (ageInt !in 5..120) {
            Toast.makeText(this, "A idade deve estar entre 5 e 120 anos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val weightKg = weightStr.replace(",", ".").toDoubleOrNull()
        var heightM = heightStr.replace(",", ".").toDoubleOrNull()

        if (weightKg == null || heightM == null) {
            Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (heightM > 3.0) heightM /= 100.0
        if (heightM <= 0.0) {
            Toast.makeText(this, "Altura inválida!", Toast.LENGTH_SHORT).show()
            return
        }

        val sex = if (binding.radioMale.isChecked) "Masculino" else "Feminino"
        val activityLevel = binding.spinnerActivity.selectedItem.toString()

        val imc = weightKg / (heightM * heightM)
        val categoria = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25   -> "Normal"
            imc < 30   -> "Sobrepeso"
            else       -> "Obesidade"
        }

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("name", name)
            putExtra("activityLevel", activityLevel)
            putExtra("imc", String.format("%.2f", imc))
            putExtra("categoria", categoria)
            putExtra("age", ageInt)
            putExtra("heightM", heightM)
            putExtra("weightKg", weightKg)
            putExtra("sex", sex)
        }

        startActivity(intent)
    }
}
