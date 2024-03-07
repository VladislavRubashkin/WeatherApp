package com.example.weatherapp.presentation.utils

import android.app.AlertDialog
import android.content.Context
import com.example.weatherapp.R

object DialogManager {
    fun locationSettingsDialog(context: Context, locationListener: LocationListener) {
        val dialog = AlertDialog.Builder(context).create()
        dialog.setTitle(R.string.location_dialog_title)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.ok)) { _, _ ->
            locationListener.onClick()
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.cancel)) { _, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    interface LocationListener {
        fun onClick()
    }

}