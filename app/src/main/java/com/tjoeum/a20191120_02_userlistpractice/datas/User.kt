package com.tjoeum.a20191120_02_userlistpractice.datas

import org.json.JSONObject
import java.io.Serializable

class User : Serializable{

    var loginId = ""
    var name = ""

    var category : Category? = null

    companion object{
        fun getUserFromJson(userJson: JSONObject) : User{

            var userObject = User()

            userObject.loginId = "(${userJson.getString("login_id")})"
            userObject.name = userJson.getString("name")
            userObject.category =  Category.getCategoryJson(userJson.getJSONObject("category"))


            return userObject

        }
    }


}