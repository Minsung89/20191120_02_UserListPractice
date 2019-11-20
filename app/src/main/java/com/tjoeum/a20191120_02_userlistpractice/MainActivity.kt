package com.tjoeum.a20191120_02_userlistpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.tjoeum.a20191120_02_userlistpractice.adapters.UserAdapter
import com.tjoeum.a20191120_02_userlistpractice.datas.User
import com.tjoeum.a20191120_02_userlistpractice.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    var userList = ArrayList<User>()

    var userAdapter : UserAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setuoEvents()
        setValues()
    }

    override fun setuoEvents() {


        userListView.setOnItemClickListener { parent, view, position, id ->

            var userdata = userList[position]

            val intent = Intent(mContext, UserDetailActivity::class.java)
            intent.putExtra("user",userdata)
            startActivity(intent)
        }


    }

    override fun setValues() {

        userAdapter = UserAdapter(mContext,R.layout.user_list_item, userList)
        userListView.adapter = userAdapter

    }

    override fun onResume() {
        super.onResume()

        getUserFromServer()
    }

    fun getUserFromServer(){
        ConnectServer.getRequestUserList(mContext,"ALL", object :ConnectServer.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                Log.d("사용자목록응답", json.toString())

                val code = json.getInt("code")

                if(code == 200){
                    val data = json.getJSONObject("data")
                    val userArr= data.getJSONArray("users")

                    //기존에 받아둔 데이터 삭제
                    userList.clear()

                    for (i in 0 until userArr.length()){

                        val userData = User.getUserFromJson(userArr.getJSONObject(i))
                        userList.add(userData)
                    }

                    runOnUiThread{
                        userAdapter!!.notifyDataSetChanged()
                    }

                }else{

                }


            }


        })
    }

}
