package com.example.sportmatch.usecases.register

import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.sportmatch.R
import com.example.sportmatch.databinding.ActivityRegistrationBinding
import com.example.sportmatch.usecases.screens.register.AthleteRegistrationActivity

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

        createImgButton(getString(R.string.athlete), R.drawable.img_athletes, AthleteRegistrationActivity::class.java)
        createImgButton(getString(R.string.establishment), R.drawable.img_establishments, EstablishmentRegistrationActivity::class.java)

//        for (i in (1..2)){
//            createImgButton()
//        }
    }

    private fun createImgButton(text: String, imgID: Int, activity: Class<*>) {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        // Create a RelativeLayout as container
        val relativeLayout = RelativeLayout(this)
        val lp = ViewGroup.LayoutParams(
            size.x * 80/100, 500
        )
        relativeLayout.layoutParams = lp

        // Create the ImageView and configure the image
        val iv = ImageView(this)
        val ivLp = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        iv.setImageResource(imgID)
        iv.layoutParams = ivLp

        // Set style to ImageView
        iv.setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY)

        // Create the TextView and set the text
        val tv = TextView(this)
        val tvLp = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        tv.text = text
        tv.gravity = Gravity.CENTER
        tvLp.addRule(RelativeLayout.CENTER_IN_PARENT)
        tv.layoutParams = tvLp

        // Set style to TextView
        tv.setTextAppearance(R.style.StyleAccentTextView)

        // Add ImageView and TextView to RelativeLayout
        relativeLayout.addView(iv)
        relativeLayout.addView(tv)

        // Adding an OnClickListener to the RelativeLayout to start the corresponding activity
        relativeLayout.setOnClickListener {
            val intent = Intent(this, activity)
            startActivity(intent)
        }

        // Add the RelativeLayout to the main container
        binding.cnrRegisterAthlete.addView(relativeLayout)
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