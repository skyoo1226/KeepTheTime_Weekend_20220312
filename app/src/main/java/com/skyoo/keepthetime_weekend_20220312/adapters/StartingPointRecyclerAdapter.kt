package com.skyoo.keepthetime_weekend_20220312.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skyoo.keepthetime_weekend_20220312.R
import com.skyoo.keepthetime_weekend_20220312.api.APIList
import com.skyoo.keepthetime_weekend_20220312.api.ServerAPI
import com.skyoo.keepthetime_weekend_20220312.datas.BasicResponse
import com.skyoo.keepthetime_weekend_20220312.datas.StartingPointData
import com.skyoo.keepthetime_weekend_20220312.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StartingPointRecyclerAdapter(
    val mContext: Context,
    val mList: List<StartingPointData>
) : RecyclerView.Adapter<StartingPointRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtStartingPointName = view.findViewById<TextView>(R.id.txtStartingPointName)
        val txtPrimary = view.findViewById<TextView>(R.id.txtPrimary)
        val imgMap = view.findViewById<ImageView>(R.id.imgMap)

        fun bind(data: StartingPointData) {

            txtStartingPointName.text = data.name

            if (data.is_primary) {
                txtPrimary.visibility = View.VISIBLE
            }
            else {
                txtPrimary.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.starting_point_list_item, parent, false)

        return MyViewHolder( view )

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bind(data)

    }

    override fun getItemCount() = mList.size

}