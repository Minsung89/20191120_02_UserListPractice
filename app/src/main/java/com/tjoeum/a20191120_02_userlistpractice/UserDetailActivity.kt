package com.tjoeum.a20191120_02_userlistpractice

import android.os.Bundle
import android.util.Log
import com.tjoeum.a20191120_02_userlistpractice.adapters.CategorySpinnerAdapter
import com.tjoeum.a20191120_02_userlistpractice.datas.Category
import com.tjoeum.a20191120_02_userlistpractice.datas.User
import com.tjoeum.a20191120_02_userlistpractice.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_user_detail.*
import org.json.JSONObject

class UserDetailActivity : BaseActivity() {

    var categoryList = ArrayList<Category>()
    var categorySpinnerAdapter: CategorySpinnerAdapter? = null
    var userdata: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        setuoEvents()
        setValues()
    }
    override fun setuoEvents() {
    }

    override fun setValues() {

        userdata = intent.getSerializableExtra("user") as User


        userIdEdt.setText(userdata?.loginId)
        userNameEdt.setText(userdata?.name)

        createdAtTxt.text = userdata?.getFormatTedCreatedAt()

        categorySpinnerAdapter = CategorySpinnerAdapter(mContext,categoryList)
        categorySelectSpinner.adapter = categorySpinnerAdapter




        getCategoryFromServer()

    }

    fun getCategoryFromServer(){

        ConnectServer.getRequestCategoryList(mContext, object: ConnectServer.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("카테고리응답", json.toString())

                var code = json.getInt("code")

                if(code == 200){
                    var data = json.getJSONObject("data")
                    var userCategory = data.getJSONArray("user_categories")


                    categoryList.clear()

                    for (i in 0..userCategory.length() - 1){

                        var uc = userCategory.getJSONObject(i)
                        var categoryData = Category.getCategoryJson(uc)
                        categoryList.add(categoryData)
                    }

                    runOnUiThread{
                        categorySpinnerAdapter?.notifyDataSetChanged()
                        var categoryIndex = categoryList.indexOf(userdata?.category)
                        categorySelectSpinner.setSelection(categoryIndex)
                    }

                }else{

                }



            }
        })
    }
}
