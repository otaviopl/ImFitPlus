package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils

import android.os.Bundle
import java.util.Locale

object ImcUtils {
    fun parseNumber(value: String?): Double? {
        if (value == null) return null
        val cleaned = value.trim().replace(",", ".")
        return cleaned.toDoubleOrNull()
    }

    fun normalizeHeightMeters(heightValue: Any?): Double? {
        val d = when (heightValue) {
            is String -> parseNumber(heightValue)
            is Number -> heightValue.toDouble()
            else -> null
        } ?: return null

        val heightM = if (d > 3.0) d / 100.0 else d
        return if (heightM > 0.0) heightM else null
    }

    fun isAgeValid(age: Int?, min: Int = 5, max: Int = 120): Boolean {
        if (age == null) return false
        return age in min..max
    }

    fun calculateImc(weightKg: Double, heightM: Double): Double {
        return if (heightM > 0.0) weightKg / (heightM * heightM) else 0.0
    }

    fun imcCategory(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Normal"
            imc < 30.0 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }

    fun formatImc(imc: Double): String {
        return String.format(Locale.ENGLISH, "%s", imc)
    }

    fun createImcBundle(
        name: String,
        activityLevel: String,
        imc: Double,
        category: String,
        age: Int,
        heightM: Double,
        weightKg: Double,
        sex: String
    ): Bundle {
        return Bundle().apply {
            putString("name", name)
            putString("activityLevel", activityLevel)
            putString("imc", formatImc(imc))
            putString("categoria", category)
            putInt("age", age)
            putDouble("heightM", heightM)
            putDouble("weightKg", weightKg)
            putString("sex", sex)
        }
    }
}