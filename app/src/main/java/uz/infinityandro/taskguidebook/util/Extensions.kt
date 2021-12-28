package uz.infinityandro.worldnews.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.displayToast(s: String) {
    Toast.makeText(this, "$s", Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}