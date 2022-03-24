package com.skyoo.keepthetime_weekend_20220312

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import com.skyoo.keepthetime_weekend_20220312.databinding.ActivityEditAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    lateinit var binding: ActivityEditAppointmentBinding

    val mSelectedDatetimeCal = Calendar.getInstance()

    //  지도에 띄워줄 목적지 표시해줄 마커 변수.
    var myMarker : Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.txtDate.setOnClickListener {
            val dsl = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    Toast.makeText(mContext, "${year}년 ${month}월 ${dayOfMonth}일", Toast.LENGTH_SHORT).show()
                    mSelectedDatetimeCal.set(year, month, dayOfMonth)

//      약속 일자 텍스트뷰의 문구를 "00월 00일" 형태로 가공해서 출력.
                    val sdf = SimpleDateFormat("M월 d일")

                    binding.txtDate.text = sdf.format(mSelectedDatetimeCal.time)
                }
            }

            val dpd = DatePickerDialog(
                mContext,
                dsl,
                mSelectedDatetimeCal.get(Calendar.YEAR),
                mSelectedDatetimeCal.get(Calendar.MONTH),
                mSelectedDatetimeCal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        binding.txtTime.setOnClickListener {
            val tsl = object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
                    mSelectedDatetimeCal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    mSelectedDatetimeCal.set(Calendar.MINUTE, minute)

                    val sdf = SimpleDateFormat("a h시 m분")
                    binding.txtTime.text = sdf.format(mSelectedDatetimeCal.time)
                }
            }
            val tpd = TimePickerDialog(
                mContext,
                tsl,
                12,
                30,
                false
            ).show()
        }

    }

    override fun setValues() {
//  지도 처음 시작 지점 좌표...난 회기역 2번 출구.
        binding.mapView.getMapAsync {
            val naverMap = it
            val cameraUpdate = CameraUpdate.scrollTo((LatLng(37.589730475241765, 127.05857781302444)))
            naverMap.moveCamera(cameraUpdate)

            val marker = Marker()
            marker.position = LatLng(37.589730475241765, 127.05857781302444)
            marker.map = naverMap

            marker.icon = MarkerIcons.BLACK
            marker.iconTintColor = Color.BLUE
            marker.width = 50
            marker.height = 80

            naverMap.setOnMapClickListener { pointF, latLng ->
//      클릭된 좌표(위치)를 위도와 경도로 표시 함.
//                Toast.makeText(
//                    mContext,
//                    "위도 : ${latLng.latitude}, 경도 : ${latLng.longitude}",
//                    Toast.LENGTH_SHORT
//                ).show()

                if (myMarker == null) {
                    myMarker = Marker()
                }
                myMarker!!.position = latLng
                myMarker!!.map = naverMap

//              val myMarker = Marker()   맨 위에 var변수로  myMarker를 사용 했기 때문에 지움.
//              myMarker.position = latLng
//              myMarker.map = naverMap
            }
        }

    }
}