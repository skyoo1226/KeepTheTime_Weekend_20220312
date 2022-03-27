package com.skyoo.keepthetime_weekend_20220312.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.skyoo.keepthetime_weekend_20220312.R
import com.skyoo.keepthetime_weekend_20220312.datas.StartingPointData

class StartingPointSpinnerAdapter(
    val mContext : Context,
    resId: Int,
    val mList: List<StartingPointData>
) : ArrayAdapter<StartingPointData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.starting_point_list_item, null)
        }

        val row = tempRow!!

        return row
    }

}