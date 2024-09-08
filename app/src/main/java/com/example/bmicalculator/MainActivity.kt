package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
<<<<<<< HEAD
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BMICalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { root, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            root.updatePadding(bottom = insets.bottom)
            windowInsets
        }

        viewModel = ViewModelProvider(this)[BMICalculatorViewModel::class.java]

        binding.buttonCalculate.setOnClickListener {
            val weight = binding.editWeight.text.toString().toDoubleOrNull()
            val heightFeet = binding.editHeightFeet.text.toString().toDoubleOrNull()
            val heightInches = binding.editHeightInches.text.toString().toDoubleOrNull()

            if (weight != null && heightFeet != null && heightInches != null) {
                viewModel.calculateBMI(weight, heightFeet, heightInches)
            } else {
                Toast.makeText(this, "Please Fill Required Fields", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.bmiResult.observe(this) { result ->
            binding.textResult.text = result
        }

        viewModel.bmiCategory.observe(this) { category ->
            binding.textMsg.text = category
        }

        viewModel.backgroundColor.observe(this) { colorResId ->
            binding.mainLnrLayout.setBackgroundColor(ContextCompat.getColor(this, colorResId))
        }
=======
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
>>>>>>> 87453b12eea9c094d1bff880b000a0ec26c60fcd
    }
}