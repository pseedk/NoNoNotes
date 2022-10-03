package com.pseedk.nononotes.utilits

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}