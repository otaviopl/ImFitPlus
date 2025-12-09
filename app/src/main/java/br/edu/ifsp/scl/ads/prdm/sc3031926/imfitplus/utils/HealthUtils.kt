package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils

object HealthUtils {

    fun calculateImc(weightKg: Double, heightCm: Double): Double {
        val heightM = heightCm / 100.0
        return weightKg / (heightM * heightM)
    }

    fun imcCategory(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }

    fun calculateTmb(sex: String, weightKg: Double, heightCm: Double, age: Int): Double {
        return if (sex.equals("M", ignoreCase = true)) {
            88.36 + (13.4 * weightKg) + (4.8 * heightCm) - (5.7 * age)
        } else {
            447.6 + (9.2 * weightKg) + (3.1 * heightCm) - (4.3 * age)
        }
    }

    fun idealWeight(heightCm: Double): Double {
        val heightM = heightCm / 100.0
        return heightM * heightM * 22
    }
}
