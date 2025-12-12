package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.db.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.db.DatabaseHelper
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.model.User

class UserDao(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insert(user: User): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", user.name)
            put("bithDate", user.bithdayDate)
            put("maxCardiac", user.maxCardiac)
            put("age", user.age)
            put("weight", user.weight)
            put("height", user.height)
            put("gender", user.gender)
            put("sportsLevel", user.sportsLevel)
            put("imc", user.imc)
            put("imcCategory", user.imcCategory)
            put("baseCalories", user.baseCalories)
            put("idealWeight", user.idealWeight)
            put("waterConsumption", user.waterConsumption)
        }
        val id = db.insert("user", null, values)
        db.close()
        return id
    }

    @SuppressLint("Range")
    fun getLatestUserByName(name: String): User? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "user",
            null,
            "name = ?",
            arrayOf(name),
            null,
            null,
            "id DESC",
            "1"
        )
        var user: User? = null
        if (cursor.moveToFirst()) {
            user = User(
                id = cursor.getLong(cursor.getColumnIndex("id")),
                name = cursor.getString(cursor.getColumnIndex("name")),
                maxCardiac = cursor.getInt(cursor.getColumnIndex("maxCardiac")),
                bithdayDate = cursor.getString(cursor.getColumnIndex("birthdate")),
                age = cursor.getInt(cursor.getColumnIndex("age")),
                weight = cursor.getDouble(cursor.getColumnIndex("weight")),
                height = cursor.getDouble(cursor.getColumnIndex("height")),
                gender = cursor.getString(cursor.getColumnIndex("gender")),
                sportsLevel = cursor.getString(cursor.getColumnIndex("sportsLevel")),
                imc = cursor.getString(cursor.getColumnIndex("imc")),
                imcCategory = cursor.getString(cursor.getColumnIndex("imcCategory")),
                baseCalories = cursor.getString(cursor.getColumnIndex("baseCalories")),
                idealWeight = cursor.getString(cursor.getColumnIndex("idealWeight")),
                waterConsumption = cursor.getString(cursor.getColumnIndex("waterConsumption")),
                createdAt = cursor.getString(cursor.getColumnIndex("createdAt"))
            )
        }
        cursor.close()
        db.close()
        return user
    }

    @SuppressLint("Range")
    fun getUsersByName(name: String): List<User> {
        val users = mutableListOf<User>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "user",
            null,
            "name = ?",
            arrayOf(name),
            null,
            null,
            "id DESC"
        )

        while (cursor.moveToNext()) {
            val userResult = User(
                id = cursor.getLong(cursor.getColumnIndex("id")),
                name = cursor.getString(cursor.getColumnIndex("name")),
                maxCardiac = cursor.getInt(cursor.getColumnIndex("maxCardiac")),
                bithdayDate = cursor.getString(cursor.getColumnIndex("birthDate")),
                age = cursor.getInt(cursor.getColumnIndex("age")),
                weight = cursor.getDouble(cursor.getColumnIndex("weight")),
                height = cursor.getDouble(cursor.getColumnIndex("height")),
                gender = cursor.getString(cursor.getColumnIndex("gender")),
                sportsLevel = cursor.getString(cursor.getColumnIndex("sportsLevel")),
                imc = cursor.getString(cursor.getColumnIndex("imc")),
                imcCategory = cursor.getString(cursor.getColumnIndex("imcCategory")),
                baseCalories = cursor.getString(cursor.getColumnIndex("baseCalories")),
                idealWeight = cursor.getString(cursor.getColumnIndex("idealWeight")),
                waterConsumption = cursor.getString(cursor.getColumnIndex("waterConsumption")),
                createdAt = cursor.getString(cursor.getColumnIndex("createdAt"))
            )
            users.add(userResult)
        }
        cursor.close()
        db.close()
        return users
    }

    @SuppressLint("Range")
    fun getAllUsers(): List<User> {
        val users = mutableListOf<User>()
        val db = dbHelper.readableDatabase
        val cursor = db.query("user", null, null, null, null, null, "id DESC")

        while (cursor.moveToNext()) {
            val user = User(
                id = cursor.getLong(cursor.getColumnIndex("id")),
                name = cursor.getString(cursor.getColumnIndex("name")),
                maxCardiac = cursor.getInt(cursor.getColumnIndex("maxCardiac")),
                bithdayDate = cursor.getString(cursor.getColumnIndex("birthDate")),
                age = cursor.getInt(cursor.getColumnIndex("age")),
                weight = cursor.getDouble(cursor.getColumnIndex("weight")),
                height = cursor.getDouble(cursor.getColumnIndex("height")),
                gender = cursor.getString(cursor.getColumnIndex("gender")),
                sportsLevel = cursor.getString(cursor.getColumnIndex("sportsLevel")),
                imc = cursor.getString(cursor.getColumnIndex("imc")),
                imcCategory = cursor.getString(cursor.getColumnIndex("imcCategory")),
                baseCalories = cursor.getString(cursor.getColumnIndex("baseCalories")),
                idealWeight = cursor.getString(cursor.getColumnIndex("idealWeight")),
                waterConsumption = cursor.getString(cursor.getColumnIndex("waterConsumption")),
                createdAt = cursor.getString(cursor.getColumnIndex("createdAt"))
            )
            users.add(user)
        }
        cursor.close()
        db.close()
        return users
    }
}
