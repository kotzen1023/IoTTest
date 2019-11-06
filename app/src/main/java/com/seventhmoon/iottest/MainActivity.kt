package com.seventhmoon.iottest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private val mTAG = MainActivity::class.java.name

    var upOnOff: Boolean = false
    var stopOnOff: Boolean = false
    var downOnOff: Boolean = false
    var oldUp: Boolean = false
    var oldStop: Boolean = false
    var oldDown: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference

        val btnUp = findViewById<Button>(R.id.btnUp)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val btnDown = findViewById<Button>(R.id.btnDown)

        val imgUp = findViewById<ImageView>(R.id.imgUp)
        val imgStop = findViewById<ImageView>(R.id.imgStop)
        val imgDown = findViewById<ImageView>(R.id.imgDown)

        imgUp!!.setOnClickListener {
            if (!upOnOff) {
                myRef.child("relay").child("ch1").child("on").setValue(true)
                imgUp.isEnabled = false
                imgStop.isEnabled = false
                imgDown.isEnabled = false
                imgUp.setImageResource(R.drawable.up_off)
                imgStop.setImageResource(R.drawable.stop_off)
                imgDown.setImageResource(R.drawable.down_off)


                btnUp.isEnabled = false
                btnStop.isEnabled = false
                btnDown.isEnabled = false
            }
        }

        btnUp!!.setOnClickListener {

            /*if (upOnOff)
                myRef.child("relay").child("ch1").child("on").setValue(false)
            else {*/
            if (!upOnOff) {
                myRef.child("relay").child("ch1").child("on").setValue(true)
                imgUp.isEnabled = false
                imgStop.isEnabled = false
                imgDown.isEnabled = false
                imgUp.setImageResource(R.drawable.up_off)
                imgStop.setImageResource(R.drawable.stop_off)
                imgDown.setImageResource(R.drawable.down_off)

                btnUp.isEnabled = false
                btnStop.isEnabled = false
                btnDown.isEnabled = false
            }
        }

        imgStop!!.setOnClickListener {
            if (!stopOnOff) {
                myRef.child("relay").child("ch2").child("on").setValue(true)
                imgUp.isEnabled = false
                imgStop.isEnabled = false
                imgDown.isEnabled = false
                imgUp.setImageResource(R.drawable.up_off)
                imgStop.setImageResource(R.drawable.stop_off)
                imgDown.setImageResource(R.drawable.down_off)

                btnUp.isEnabled = false
                btnStop.isEnabled = false
                btnDown.isEnabled = false
            }
        }

        btnStop!!.setOnClickListener {

            /*if (stopOnOff)
                myRef.child("relay").child("ch2").child("on").setValue(false)
            else*/
            if (!stopOnOff) {
                myRef.child("relay").child("ch2").child("on").setValue(true)
                imgUp.isEnabled = false
                imgStop.isEnabled = false
                imgDown.isEnabled = false
                imgUp.setImageResource(R.drawable.up_off)
                imgStop.setImageResource(R.drawable.stop_off)
                imgDown.setImageResource(R.drawable.down_off)

                btnUp.isEnabled = false
                btnStop.isEnabled = false
                btnDown.isEnabled = false
            }
        }

        imgDown!!.setOnClickListener {
            if (!downOnOff) {
                myRef.child("relay").child("ch3").child("on").setValue(true)
                imgUp.isEnabled = false
                imgStop.isEnabled = false
                imgDown.isEnabled = false
                imgUp.setImageResource(R.drawable.up_off)
                imgStop.setImageResource(R.drawable.stop_off)
                imgDown.setImageResource(R.drawable.down_off)

                btnUp.isEnabled = false
                btnStop.isEnabled = false
                btnDown.isEnabled = false
            }
        }

        btnDown!!.setOnClickListener {

            /*if (downOnOff)
                myRef.child("relay").child("ch3").child("on").setValue(false)
            else {*/
            if (!downOnOff) {
                myRef.child("relay").child("ch3").child("on").setValue(true)
                imgUp.isEnabled = false
                imgStop.isEnabled = false
                imgDown.isEnabled = false
                imgUp.setImageResource(R.drawable.up_off)
                imgStop.setImageResource(R.drawable.stop_off)
                imgDown.setImageResource(R.drawable.down_off)

                btnUp.isEnabled = false
                btnStop.isEnabled = false
                btnDown.isEnabled = false
            }
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                upOnOff = p0.child("relay").child("ch1").child("on").value as Boolean
                stopOnOff = p0.child("relay").child("ch2").child("on").value as Boolean
                downOnOff = p0.child("relay").child("ch3").child("on").value as Boolean
                Log.e(mTAG, "upOnOff is: $upOnOff")
                Log.e(mTAG, "stopOnOff is: $stopOnOff")
                Log.e(mTAG, "downOnOff is: $downOnOff")

                if (oldUp && !upOnOff) { //true-> false
                    imgUp.isEnabled = true
                    imgStop.isEnabled = true
                    imgDown.isEnabled = true
                    imgUp.setImageResource(R.drawable.up)
                    imgStop.setImageResource(R.drawable.stop)
                    imgDown.setImageResource(R.drawable.down)

                    btnUp.isEnabled = true
                    btnStop.isEnabled = true
                    btnDown.isEnabled = true
                } else if (oldStop &&  !stopOnOff) {
                    imgUp.isEnabled = true
                    imgStop.isEnabled = true
                    imgDown.isEnabled = true
                    imgUp.setImageResource(R.drawable.up)
                    imgStop.setImageResource(R.drawable.stop)
                    imgDown.setImageResource(R.drawable.down)

                    btnUp.isEnabled = true
                    btnStop.isEnabled = true
                    btnDown.isEnabled = true
                } else if (oldDown && !downOnOff) {
                    imgUp.isEnabled = true
                    imgStop.isEnabled = true
                    imgDown.isEnabled = true
                    imgUp.setImageResource(R.drawable.up)
                    imgStop.setImageResource(R.drawable.stop)
                    imgDown.setImageResource(R.drawable.down)

                    btnUp.isEnabled = true
                    btnStop.isEnabled = true
                    btnDown.isEnabled = true
                } else {
                    Log.d(mTAG, "not true -> false")
                }


                if (upOnOff) {
                    btnUp.text = "Up (On)"
                } else {
                    btnUp.text = "Up (Off)"
                }

                if (stopOnOff) {
                    btnStop.text = "Stop (On)"
                } else {
                    btnStop.text = "Stop (Off)"
                }

                if (downOnOff) {
                    btnDown.text = "Down (On)"
                } else {
                    btnDown.text = "Down (Off)"
                }

                oldUp = upOnOff
                oldStop = stopOnOff
                oldDown = downOnOff
                //btnUp.isEnabled = true
                //btnStop.isEnabled = true
                //btnDown.isEnabled = true
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })

    }
}
