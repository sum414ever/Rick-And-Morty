package com.simple.rickandmorty

import android.content.res.Configuration
import android.graphics.Color
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

fun TextView.changeColorByTheme(currentNightMode: Int) {
    when (currentNightMode) {
        Configuration.UI_MODE_NIGHT_NO -> this.setTextColor(Color.BLACK)

        Configuration.UI_MODE_NIGHT_YES -> this.setTextColor(Color.WHITE)

        Configuration.UI_MODE_NIGHT_UNDEFINED -> this.setTextColor(Color.BLACK)
    }
}

fun ConstraintLayout.setRippleEffect(currentNightMode: Int) {
    when (currentNightMode) {
        Configuration.UI_MODE_NIGHT_NO -> this.setBackgroundResource(R.drawable.ripple_effect_white)

        Configuration.UI_MODE_NIGHT_YES -> this.setBackgroundResource(R.drawable.ripple_effect_black)

        Configuration.UI_MODE_NIGHT_UNDEFINED -> this.setBackgroundResource(R.drawable.ripple_effect_white)
    }
}