package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateImcBinding
import kotlin.math.pow

class CalculateImcActivity : AppCompatActivity() {
    private lateinit var binding: CalculateImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateImcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar(binding)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.activity_levels,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerActivity.adapter = adapter

        binding.calcImcbtn.setOnClickListener { handleImcButtonClick() }
    }

    private fun setupToolbar(binding: CalculateImcBinding) {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Calculadora de IMC"
    }

    private fun handleImcButtonClick() {
        val name = binding.inputName.text.toString().trim()
        val weightStr = binding.inputWeight.text.toString().trim()
        val heightStr = binding.inputHeight.text.toString().trim()

        if (name.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.replace(",", ".").toDoubleOrNull()
        var height = heightStr.replace(",", ".").toDoubleOrNull()

        if (weight == null || height == null) {
            Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (height > 3.0) height /= 100.0

        if (height <= 0.0) {
            Toast.makeText(this, "Altura inválida!", Toast.LENGTH_SHORT).show()
            return
        }

        val imc = weight / (height * height)

        val categoria = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }

        val activityLevel = binding.spinnerActivity.selectedItem.toString()

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("name", name)
            putExtra("activityLevel", activityLevel)
            putExtra("imc", String.format("%.2f", imc))
            putExtra("categoria", categoria)
        }
        startActivity(intent)
    }
}
