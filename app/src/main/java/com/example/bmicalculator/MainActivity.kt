package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    }
}