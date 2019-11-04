package com.seventhmoon.iottest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Button
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private val mTAG = MainActivity::class.java.name

    var upOnOff: Boolean = false
    var stopOnOff: Boolean = false
    var downOnOff: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference

        val btnUp = findViewById<Button>(R.id.btnUp)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val btnDown = findViewById<Button>(R.id.btnDown)

        btnUp!!.setOnClickListener {

            /*if (upOnOff)
                myRef.child("relay").child("ch1").child("on").setValue(false)
            else {*/
            if (!upOnOff) {
                myRef.child("relay").child("ch1").child("on").setValue(true)
                btnDown.isEnabled = false
            }
        }

        btnStop!!.setOnClickListener {

            /*if (stopOnOff)
                myRef.child("relay").child("ch2").child("on").setValue(false)
            else*/
            if (!stopOnOff) {
                myRef.child("relay").child("ch2").child("on").setValue(true)
                btnStop.isEnabled = true
            }
        }

        btnDown!!.setOnClickListener {

            /*if (downOnOff)
                myRef.child("relay").child("ch3").child("on").setValue(false)
            else {*/
            if (!downOnOff) {
                myRef.child("relay").child("ch3").child("on").setValue(true)
                btnUp.isEnabled = false
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


                if (upOnOff || downOnOff ) {
                    btnUp.isEnabled = false
                    btnDown.isEnabled = false
                } else {
                    btnUp.isEnabled = true
                    btnDown.isEnabled = true
                }

                if (upOnOff)
                    btnUp.text = "Up (On)"
                else
                    btnUp.text = "Up (Off)"

                if (stopOnOff)
                    btnStop.text = "Stop (On)"
                 else
                    btnStop.text = "Stop (Off)"

                if (downOnOff)
                    btnDown.text = "Down (On)"
                else
                    btnDown.text = "Down (Off)"



                //btnUp.isEnabled = true
                //btnStop.isEnabled = true
                //btnDown.isEnabled = true
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })

    }
}
