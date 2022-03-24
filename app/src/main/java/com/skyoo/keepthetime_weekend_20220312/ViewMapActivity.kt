package com.skyoo.keepthetime_weekend_20220312

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.skyoo.keepthetime_weekend_20220312.databinding.ActivityViewMapBinding

// 네이버 지도를 화면 기득 띄우기 + 약속 장소의 좌표로 카메라 이동/ 마커 띄우기

class ViewMapActivity : BaseActivity() {

    lateinit var binding : ActivityViewMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_map)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}