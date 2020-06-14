package com.intertech.floorestimator.util

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan

class TextUtils {
    companion object {
        fun getStringFromRes(context: Context, stringRes: Int): String {
            return context.getString(stringRes)
        }

        fun createUnderlineText(text: String, fromIndex: Int = 0, toIndex: Int = text.length): SpannableString {
            val spannableString = SpannableString(text)
            spannableString.setSpan(UnderlineSpan(), fromIndex, toIndex, 0)
            return spannableString
        }
    }
}