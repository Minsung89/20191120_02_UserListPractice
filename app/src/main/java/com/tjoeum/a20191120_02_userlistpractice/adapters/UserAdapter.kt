package com.tjoeum.a20191120_02_userlistpractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tjoeum.a20191120_02_userlistpractice.R
import com.tjoeum.a20191120_02_userlistpractice.datas.User
import java.util.zip.Inflater

class UserAdapter(context: Context, res: Int, userList: ArrayList<User>) : ArrayAdapter<User>(context,res,userList){

    var mContext = context
    var mList = userList
    var inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow == null){
            tempRow = inf.inflate(R.layout.user_list_item,null)
        }

        var row = tempRow!!

        var userName = row.findViewById<TextView>(R.id.userNameTxt)
        var userid = row.findViewById<TextView>(R.id.userIdTxt)

        userName.text = mList[position].name
        userid.text = mList[position].loginId

        return row
    }

}