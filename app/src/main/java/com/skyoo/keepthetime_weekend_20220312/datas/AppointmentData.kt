package com.skyoo.keepthetime_weekend_20220312.datas

class AppointmentData(
    val id: Int,
    val user_id: Int,
    val title: String,
    val datetime: String,
    val start_place: String,
    val start_latitude: Double,
    val start_longitude: Double,
    val place: String,
    val latitude: Double,
    val longitude: Double,
) {
}