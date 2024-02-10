package com.example.weatherapp.presentation.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
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

    fun searchByCity(context: Context, searchListener: SearchListener) {
        val builder = AlertDialog.Builder(context)
        val edCity = EditText(context)
        builder.setView(edCity)
        val dialog = builder.create()
        dialog.setTitle(R.string.search_city_dialog_title)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.ok)) { _, _ ->
            // Передаём через поле edCity название города, для которого будет определяться погода
            searchListener.onClick(edCity.text.toString())
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

    interface SearchListener {
        fun onClick(city: String)
    }
}