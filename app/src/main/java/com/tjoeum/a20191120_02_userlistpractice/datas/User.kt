package com.tjoeum.a20191120_02_userlistpractice.datas

import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class User : Serializable{

    var loginId = ""
    var name = ""
    var createdAt = Calendar.getInstance()
    var category : Category? = null

    val printTimeFormat = SimpleDateFormat("y년 M월 d일")

    fun getFormatTedCreatedAt() : String{
        return printTimeFormat.format(createdAt.time)
    }

    companion object{
        val serverTimeParseFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")



        fun getUserFromJson(userJson: JSONObject) : User{

            var userObject = User()

            userObject.loginId = "(${userJson.getString("login_id")})"
            userObject.name = userJson.getString("name")

            var testCreateAt = userJson.getString("created_at")

            userObject.createdAt.time = serverTimeParseFormat.parse(testCreateAt)

            userObject.category =  Category.getCategoryJson(userJson.getJSONObject("category"))


            return userObject

        }
    }


}