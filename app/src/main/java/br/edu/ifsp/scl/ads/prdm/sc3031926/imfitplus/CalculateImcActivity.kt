package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
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

        binding.calcImcbtn.setOnClickListener { handleImcButtonClick() }
    }

    private fun setupToolbar(binding: CalculateImcBinding) {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Calculadora de IMC"
    }

    private fun handleImcButtonClick() {
        val name = binding.inputName.text.toString()
        val weightStr = binding.inputWeight.text.toString()
        val heightStr = binding.inputHeight.text.toString()

        if (name.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.toDoubleOrNull()
        val height = heightStr.replace(",", ".").toDoubleOrNull()

        if (weight == null || height == null || height == 0.0) {
            Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show()
            return
        }

        val imc = weight / height.pow(2)
        val categoria = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }

        // Envia os dados para a ResultActivity
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("name", name)
            putExtra("imc", String.format("%.2f", imc))
            putExtra("categoria", categoria)
        }
        startActivity(intent)
    }
}
