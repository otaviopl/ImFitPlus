package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateImcBinding
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.ImcUtils.calculateImc
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.ImcUtils.createImcBundle
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.ImcUtils.imcCategory
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.ImcUtils.isAgeValid
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.ImcUtils.normalizeHeightMeters
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils.ImcUtils.parseNumber

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

        if (!isAgeValid(ageInt)) {
            Toast.makeText(this, "Informe uma idade válida entre 5 e 120 anos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val weightKg = parseNumber(weightStr)
        val heightM = normalizeHeightMeters(heightStr)

        if (weightKg == null || heightM == null) {
            Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show()
            return
        }

        val sex = if (binding.radioMale.isChecked) "Masculino" else "Feminino"
        val activityLevel = binding.spinnerActivity.selectedItem.toString()

        val imc = calculateImc(weightKg, heightM)

        val intent = Intent(this, ResponseActivity::class.java).apply {
            putExtras(createImcBundle(
                name = name,
                activityLevel = activityLevel,
                imc = imc,
                category = imcCategory(imc),
                age = ageInt ?: 0,
                heightM = heightM,
                weightKg = weightKg,
                sex = sex
            ))
        }

        startActivity(intent)
    }
}
