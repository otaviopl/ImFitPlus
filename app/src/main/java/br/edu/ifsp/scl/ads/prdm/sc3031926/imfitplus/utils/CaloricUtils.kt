package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.utils

import kotlin.math.roundToInt

object CaloricUtils {
    fun calculateBmrReference(sex: String, weightKg: Double, heightM: Double, age: Int): Double {
        return if (sex.equals("Masculino", ignoreCase = true) ||
            sex.equals("M", ignoreCase = true) ||
            sex.equals("MALE", ignoreCase = true)) {
            66 + (13.7 * weightKg) + (5 * heightM * 100) - (6.8 * age)
        } else {
            655 + (9.6 * weightKg) + (1.8 * heightM * 100) - (4.7 * age)
        }
    }

    fun activityFactor(level: String): Double {
        val lv = level.lowercase()
            .replace("á", "a")
            .replace("à", "a")
            .replace("ã", "a")
            .replace("é", "e")
            .trim()

        return when (lv) {
            "sedentario", "sedentário", "sedentário" -> 1.2
            "leve", "leve (ex: caminhada)" -> 1.375
            "moderado", "moderado (ex: 3-4x/sem)" -> 1.55
            "intenso", "intenso (ex: 6-7x/sem)" -> 1.725
            else -> 1.2
        }
    }

    fun calculateTdeeFromLevel(bmr: Double, activityLevel: String): Double {
        val factor = activityFactor(activityLevel)
        return bmr * factor
    }

    fun formatCaloricResult(name: String, bmr: Double, activityLevel: String, tdee: Double): String {
        return """
        Olá, $name!
        TMB (Taxa Metabólica Basal): ${bmr.roundToInt()} kcal/dia
        Fator de atividade ($activityLevel): ${"%.3f".format(activityFactor(activityLevel))}
        Gasto Calórico Diário (TDEE): ${tdee.roundToInt()} kcal/dia
    """.trimIndent()
    }

    fun idealWeightFromHeightM(heightM: Double, targetImc: Double = 22.0): Double {
        return targetImc * (heightM * heightM)
    }

}
