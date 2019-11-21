package com.tjoeum.a20191120_02_userlistpractice.datas

import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class User : Serializable{

    var loginId = ""
    var name = ""
    var createdAt = Calendar.getInstance()
    var expireDate :Calendar? = null
    var category : Category? = null

    val printTimeFormat = SimpleDateFormat("y년 M월 d일")

    fun getFormatTedCreatedAt() : String{
        return printTimeFormat.format(createdAt.time)
    }

    fun getExireDateString() : String{

        if(this.expireDate != null)
            return expireDataParseFormat.format(this.expireDate!!.time)
        else
            return "만료일자가 설정되징 않았씀까"
    }

    companion object{
        val serverTimeParseFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val expireDataParseFormat = SimpleDateFormat("yyyy-MM-dd")



        fun getUserFromJson(userJson: JSONObject) : User{

            var userObject = User()

            userObject.loginId = "(${userJson.getString("login_id")})"
            userObject.name = userJson.getString("name")

            var testCreateAt = userJson.getString("created_at")

            userObject.createdAt.time = serverTimeParseFormat.parse(testCreateAt)

            if(!userJson.isNull("expire_data")){

                val expireDateStr = userJson.getString("expire_date")
                userObject.createdAt = Calendar.getInstance()
                userObject.expireDate?.time = expireDataParseFormat.parse(expireDateStr)

            }else{
                userObject.expireDate = null
            }



            userObject.category =  Category.getCategoryJson(userJson.getJSONObject("category"))


            return userObject

        }
    }


}