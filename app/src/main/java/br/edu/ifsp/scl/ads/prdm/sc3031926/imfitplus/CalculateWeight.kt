package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateCaloricBinding
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.databinding.CalculateWeightBinding

class CalculateWeight: AppCompatActivity() {
    private lateinit var binding: CalculateWeightBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalculateWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = "Seu peso ideal"
    }
    private fun calculateIdealWeight(heightM: Double): Double {
        return 22 * (heightM * heightM)
    }



}