package com.example.nancalculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var Items: TextView
    private lateinit var gamematerials: String
    var stringList = mutableListOf<String>()
    private lateinit var showPopupInstruct : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showPopupInstruct = findViewById(R.id.instrucButton)
        showPopupInstruct.setOnClickListener {
            showPopup()
        }

        Items = findViewById(R.id.selectedItems)

    }

    private fun showPopup() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup, null)

        val width = 800
        val height = 500

        val instructWindow = PopupWindow(popupView, width, height, true)
        instructWindow.showAtLocation(popupView, Gravity.BOTTOM, 20, 100)

        val closeButton = popupView.findViewById<Button>(R.id.closeButton)
        closeButton.setOnClickListener {
            instructWindow.dismiss()
        }
    }


    fun onmaterialsButtonClick(view: View) {
        val zoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        view.startAnimation(zoomInAnimation)

        gamematerials = when (view.id) {
            R.id.basketbutton -> "Basket"
            R.id.batbutton -> "Bat"
            R.id.ballbutton -> "Ball"

            else -> ""
        }
        stringList.add(gamematerials)
        Items.text = "You clicked $stringList"
    }

    fun onGetResultsButtonClick(view: View){
        val selectedItems = stringList
        val itemMessage = when (selectedItems.size){
            0 -> "Please select an item"
            1 -> "${selectedItems[0]} is chosen"
            2 -> {
                if ("Basket" in selectedItems && "Ball" in selectedItems) {
                    "The game is Basketball"
                } else if ("Bat" in selectedItems && "Ball" in selectedItems) {
                    "The game is Baseball"
                } else if ("Basket" in selectedItems && "Bat" in selectedItems){
                    "The game does not exist"
                } else{
                    "Ball"
                }
            }
            3 -> "BaseKet Ball"
            else -> "Just make your own sport man."

        }
        Items.text = itemMessage
        stringList.clear()
    }
}