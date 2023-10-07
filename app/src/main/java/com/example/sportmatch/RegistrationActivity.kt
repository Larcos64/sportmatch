package com.example.sportmatch

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sportmatch.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layout = findViewById<LinearLayout>(R.id.cnrRegisterAthlete)
        layout.setGravity(Gravity.CENTER);
//        addImage(layout,"Deportista", R.drawable.img_athletes)
//        addImage(layout,"Establecimiento", R.drawable.img_establishments)

        createImgButton("Deportista", R.drawable.img_athletes)
        createImgButton("Establecimiento", R.drawable.img_establishments)

//        for (i in (1..2)){
//            createImgButton()
//        }
    }

    private fun createImgButton(text: String, imgID: Int) {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val iv = ImageView(this)
        val lp = ViewGroup.LayoutParams(
            size.x * 80/100, 500
        )

        iv.setImageResource(imgID)
        iv.layoutParams = lp
        binding.cnrRegisterAthlete.addView(iv)
    }
    private fun createImgButtonV1() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val iv = ImageView(this)
        val lp = ViewGroup.LayoutParams(
            size.x * 80/100, 500
        )

        iv.setImageResource(R.drawable.img_establishments)
        iv.layoutParams = lp
        binding.cnrRegisterAthlete.addView(iv)
    }

    fun addImage(layout: LinearLayout, text: String, imgID: Int) {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val regType = layoutInflater.inflate(R.layout.layout_registration, null)
        val iv = regType.findViewById<ImageView>(R.id.imgRegisterType)
        iv.setImageResource(imgID)

        val lp = ViewGroup.LayoutParams(
            size.x * 80/100, 500
        )
        iv.layoutParams = lp

        val tv = regType.findViewById<TextView>(R.id.tvRegisterType)
        tv.setText(text)
//        layout.setHorizontalGravity(Gravity.CENTER)
//        binding.cnrRegisterAthlete.setVerticalGravity(Gravity.CENTER)
        layout.addView(regType)
    }

    fun addImageV2(layout: LinearLayout, text: String, imgID: Int) {
        val regType = layoutInflater.inflate(R.layout.layout_registration, null)
        val iv = regType.findViewById<ImageView>(R.id.imgRegisterType)
        iv.setImageResource(imgID)

        val tv = regType.findViewById<TextView>(R.id.tvRegisterType)
        tv.text = text

        layout.addView(regType)
    }
}