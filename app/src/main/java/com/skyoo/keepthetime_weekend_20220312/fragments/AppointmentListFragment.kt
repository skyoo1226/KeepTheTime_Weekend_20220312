package com.skyoo.keepthetime_weekend_20220312.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.skyoo.keepthetime_weekend_20220312.R
import com.skyoo.keepthetime_weekend_20220312.adapters.AppointmentRecyclerAdapter
import com.skyoo.keepthetime_weekend_20220312.databinding.FragmentAppointmentListBinding
import com.skyoo.keepthetime_weekend_20220312.datas.AppointmentData
import com.skyoo.keepthetime_weekend_20220312.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentListFragment : BaseFragment() {

    lateinit var binding: FragmentAppointmentListBinding

    val mAppointmentList = ArrayList<AppointmentData>()

    lateinit var mAdapter: AppointmentRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

    }

    override fun setValues() {
        mAdapter = AppointmentRecyclerAdapter(mContext, mAppointmentList)
        binding.appointmentRecyclerView.adapter = mAdapter
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }
//  onResume을 이용, 이 화면이 나타날때마다 실행.
    override fun onResume() {
        super.onResume()
//  이화면으로 돌아 올때마다, 내 약속목록 새로 고침(자동 새로고침)
        getMyAppointmentListFromServer()
    }

    fun getMyAppointmentListFromServer() {

//   APIList 먼저 추가해야 됨. getMyAppointmentListFromServer()함수를 API로 쓰기 위해서
        apiList.getRequestMyAppointment().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {
                    mAppointmentList.clear()  //기존에 들어 있던 약속 목록 삭제.

                    val br = response.body()!!   // 서버가 내려준 약속목록 ArrayList에 등록. 단, br변수는 code,message,date만 있음.
                    mAppointmentList.addAll( br.data.appointments )

                    mAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}