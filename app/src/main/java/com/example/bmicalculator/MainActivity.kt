package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_calculate.setOnClickListener {
            if (edit_weight.text.toString() != "" && edit_height_feet.text.toString() != ""
                && edit_height_inches.text.toString() != ""
            ) {
                val wt = (edit_weight.text.toString()).toDouble()
                val htFeet = (edit_height_feet.text.toString()).toDouble()
                val htInch = (edit_height_inches.text.toString()).toDouble()

                val totalInches = (htFeet * 12) + htInch
                val totalCentimeter = totalInches * 2.54

                val totalMeter = totalCentimeter / 100

                val bmi = wt / (totalMeter * totalMeter)

                text_title.text = "Your BMI"
                text_result.text = "${bmi.toInt()}"

                when {
                    bmi > 25 -> {
                        Toast.makeText(this, "You are OverWeight", Toast.LENGTH_SHORT).show()

                        text_msg.text = "You are OverWeight"

                        main_lnr_layout.setBackgroundColor(resources.getColor(R.color.colorRed))

                    }
                    bmi < 18 -> {
                        Toast.makeText(this, "You are UnderWeight", Toast.LENGTH_SHORT).show()

                        text_msg.text = "You are UnderWeight"

                        main_lnr_layout.setBackgroundColor(resources.getColor(R.color.colorBlue))

                    }
                    else -> {
                        Toast.makeText(this, "You are Healthy", Toast.LENGTH_SHORT).show()

                        text_msg.text = "You are Healthy"

                        main_lnr_layout.setBackgroundColor(resources.getColor(R.color.colorGreen))
                    }
                }

            } else {
                Toast.makeText(this@MainActivity, "Please Fill Required Fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}