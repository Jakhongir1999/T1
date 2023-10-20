package com.example.t1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.t1.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvBack.setOnClickListener {
            binding.tvL.visibility = View.VISIBLE
            binding.tvL1.visibility = View.GONE
            binding.tvBack.visibility = View.GONE
        }

        binding.btnEq.setOnClickListener {
            binding.tvL.visibility = View.GONE
            binding.tvL1.visibility = View.VISIBLE
            binding.tvBack.visibility = View.VISIBLE
            if (binding.edAb.text.isNullOrEmpty() || binding.edAC.text.isNullOrEmpty() || binding.edBC.text.isNullOrEmpty()) {

                Toast.makeText(this, "Maydonlarni to'ldiring!", Toast.LENGTH_LONG).show()
            } else {
                val edAB = binding.edAb.text.toString().toDouble() ?: 0.0
                val edBC = binding.edBC.text.toString().toDouble() ?: 0.0
                val edAC = binding.edAC.text.toString().toDouble() ?: 0.0
                val U1 = Math.sqrt(
                    1 / 12.0 * Math.abs(
                        Math.pow(
                            (Math.sqrt(3.0) * edAB + sqrt(
                                4 * Math.pow(
                                    edBC,
                                    2.0
                                ) - Math.pow(
                                    ((Math.pow(edBC, 2.0) - Math.pow(
                                        edAC,
                                        2.0
                                    )) / edAB) + edAB, 2.0
                                )
                            )), 2.0
                        ) + (Math.pow(((edBC * edBC - edAC * edAC) / edAB), 2.0))
                    )
                )
                val U2 = Math.sqrt(
                    1 / 12.0 * Math.abs(
                        Math.pow(
                            (Math.sqrt(3.0) * edAB - sqrt(
                                4 * Math.pow(
                                    edBC,
                                    2.0
                                ) - Math.pow(
                                    ((Math.pow(edBC, 2.0) - Math.pow(
                                        edAC,
                                        2.0
                                    )) / edAB) + edAB, 2.0
                                )
                            )), 2.0
                        ) + (Math.pow(((edBC * edBC - edAC * edAC) / edAB), 2.0))
                    )
                )
                if (U1 == 0.0 || U2 == 0.0) {
                    Toast.makeText(this, "Normal ish rejimida!", Toast.LENGTH_LONG).show()
                } else {
                    val u1 = U1.toString().substring(0, 6)
                    val u2 = U2.toString().substring(0, 6)
                    val k2Urux = 2.0
                    val k2u = (U2 / U1) * 100.0
                    val sub = k2u.toString().substring(0, 6)

                    if (k2u > k2Urux) {
                        binding.tvResult.text =
                            "Nosimmetrik ish rejimda\nK2U = $sub%\nU1 = $u1 Volt\nU2 = $u2 Volt\nK2U Rux = 2.0%"
//                            Toast.makeText(this, "No simmetrik ish rejimda", Toast.LENGTH_LONG).show()
                    } else {
                        binding.tvResult.text = "Normal ish rejimda\nK2U = $sub%\nU1 = $u1 Volt\nU2 = $u2 Volt\nK2U Rux = 2.0%"
//                            Toast.makeText(this, "Normal ish rejimda", Toast.LENGTH_LONG).show()
                    }

                }
            }

        }
        binding.tvResult.text = "K2U-%\nU1-Volt \nU2-Volt\nK2U Rux = 2.0%"


    }
}