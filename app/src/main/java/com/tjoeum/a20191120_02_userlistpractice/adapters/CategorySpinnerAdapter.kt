package com.tjoeum.a20191120_02_userlistpractice.adapters
import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tjoeum.a20191120_02_userlistpractice.R
import com.tjoeum.a20191120_02_userlistpractice.datas.Category
import java.util.zip.Inflater

class CategorySpinnerAdapter(context: Context, res: Int, list: ArrayList<Category>) : ArrayAdapter<Category>(context,res,list){

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)


    constructor(context: Context, list: ArrayList<Category>) : this(context, R.layout.category_spinner_list_item, list)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow == null){
            tempRow = inf.inflate(R.layout.category_spinner_list_item,null)
        }

        var row = tempRow!!

        var categoryColorImg = row.findViewById<ImageView>(R.id.categoryColorImg)
        var categoryTitleTxt = row.findViewById<TextView>(R.id.categoryTitleTxt)

        var data = mList[position]

        categoryTitleTxt.text = data.title

        categoryColorImg.background.setColorFilter(Color.parseColor(data.color),PorterDuff.Mode.SRC_ATOP)


        return row
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if(tempRow == null){
            tempRow = inf.inflate(R.layout.category_spinner_list_item,null)
        }

        var row = tempRow!!

        var categoryColorImg = row.findViewById<ImageView>(R.id.categoryColorImg)
        var categoryTitleTxt = row.findViewById<TextView>(R.id.categoryTitleTxt)

        var data = mList[position]

        categoryTitleTxt.text = data.title

        categoryColorImg.background.setColorFilter(Color.parseColor(data.color),PorterDuff.Mode.SRC_ATOP)

        return row

    }


}