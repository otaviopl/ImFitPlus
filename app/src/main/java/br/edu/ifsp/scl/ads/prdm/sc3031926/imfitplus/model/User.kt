package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.model

data class User(
    val id: Long? = null,
    val bithdayDate: String,
    val age: Int,
    val maxCardiac: Int,
    val name: String,
    val weight: Double,
    val height: Double,
    val gender: String,
    val sportsLevel: String,
    val imc: String,
    val imcCategory: String,
    val baseCalories: String,
    val idealWeight: String,
    val waterConsumption: String,
    val createdAt: String? = null
)
