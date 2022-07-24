package com.onix.internship.okucherenko.ui.points

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.entity.Point

class PointsViewModel : BaseViewModel() {

    private val pointsList = mutableListOf<Point>()

    private val _points = MutableLiveData<MutableList<Point>>()
    val points: LiveData<MutableList<Point>> = _points

    fun addPoint(point: Point) {
        pointsList.add(point)
        _points.value = pointsList
    }

    fun onDelete(id: Int){

    }

}