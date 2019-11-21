package com.tjoeum.a20191120_02_userlistpractice.datas

import org.json.JSONObject
import java.io.Serializable

class Category : Serializable{

    var id = 0
    var title = ""
    var color = ""

    companion object{

        fun getCategoryJson(categoryJson: JSONObject): Category{

            var category = Category()

            category.id = categoryJson.getInt("id")
            category.title = categoryJson.getString("title")
            category.color = categoryJson.getString("color")

            return category
        }

    }


    override fun equals(other: Any?): Boolean {
        var result = false

        var otherCategory = other as Category
        if(otherCategory.id ==  this.id){
            result = true
        }

        return result

    }
}