package com.software.listapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref constructor(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    companion object {
        private const val NAME = "ListApp"
        private const val MODE = Context.MODE_PRIVATE
    }

}