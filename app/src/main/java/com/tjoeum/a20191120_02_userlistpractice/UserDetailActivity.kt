package com.tjoeum.a20191120_02_userlistpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjoeum.a20191120_02_userlistpractice.utils.ConnectServer
import org.json.JSONObject

class UserDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        setuoEvents()
        setValues()
    }
    override fun setuoEvents() {
    }

    override fun setValues() {

    }

    fun getCategoryFromServer(){

        ConnectServer.getRequestCategoryList(mContext, object: ConnectServer.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("사용자목록응답", json.toString())
            }
        })
    }
}
