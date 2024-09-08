package com.example.bmicalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BMICalculatorViewModel : ViewModel() {

    private val _bmiResult = MutableLiveData<String>()
    val bmiResult: LiveData<String> get() = _bmiResult

    private val _bmiCategory = MutableLiveData<String>()
    val bmiCategory: LiveData<String> get() = _bmiCategory

    private val _backgroundColor = MutableLiveData<Int>()
    val backgroundColor: LiveData<Int> get() = _backgroundColor

    fun calculateBMI(weight: Double, heightFeet: Double, heightInches: Double) {
        val totalInches = (heightFeet * 12) + heightInches
        val totalCentimeter = totalInches * 2.54
        val totalMeter = totalCentimeter / 100
        val bmi = weight / (totalMeter * totalMeter)

        _bmiResult.value = "${bmi.toInt()}"

        when {
            bmi > 25 -> {
                _bmiCategory.value = "You are OverWeight"
                _backgroundColor.value = R.color.colorRed
            }
            bmi < 18 -> {
                _bmiCategory.value = "You are UnderWeight"
                _backgroundColor.value = R.color.colorBlue
            }
            else -> {
                _bmiCategory.value = "You are Healthy"
                _backgroundColor.value = R.color.colorGreen
            }
        }
    }
}