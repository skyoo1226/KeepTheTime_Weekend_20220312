package com.skyoo.keepthetime_weekend_20220312.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skyoo.keepthetime_weekend_20220312.R
import com.skyoo.keepthetime_weekend_20220312.ViewMapActivity
import com.skyoo.keepthetime_weekend_20220312.datas.AppointmentData
import com.skyoo.keepthetime_weekend_20220312.datas.UserData

class AppointmentRecyclerAdapter(
    val mContext: Context,
    val mList: List<AppointmentData> //상속처만 변경
) : RecyclerView.Adapter<AppointmentRecyclerAdapter.MyViewHolder>() {

    inner class  MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

//  멤버변수 다시 작성, 실제 사용할 UI들을 가져와서 담아두자.
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtDateTime = view.findViewById<TextView>(R.id.txtDateTime)
        val txtPlaceName = view.findViewById<TextView>(R.id.txtPlaceName)
        val imgMap = view.findViewById<ImageView>(R.id.imgMap)

//  받아올 데이터만 AppointmentData로 변경
        fun bind( data: AppointmentData )  {
            txtTitle.text = data.title
            txtDateTime.text = data.datetime
            txtPlaceName.text = data.place

            imgMap.setOnClickListener {
//      지도만 크게 보는 화면으로 이동.
                val myIntent = Intent(mContext, ViewMapActivity::class.java)

//      어떤 약속을 지도로 보여주는지 첨부
                myIntent.putExtra("appointment", data)

//          startActivity는 화면, 프래그먼트에서만 상속 받는 기능임 => Context가 상속. 그래서 mContext이용.
                mContext.startActivity(myIntent)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//  xml 경로만 변경
        val view = LayoutInflater.from(mContext).inflate(R.layout.appointment_list_item, parent, false)
        return  MyViewHolder( view )

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//  실제 출력할 데이터
        val data = mList[position]

//  MyViewHolder도 일종의 클래스 : 멤버변수 / 함수를 가지고 있을 수 있다. => 활용하자.
        holder.bind( data )

    }

    override fun getItemCount() = mList.size  // 목록의 갯수가 리턴.

}