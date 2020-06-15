package com.intertech.floorestimator.util

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.intertech.floorestimator.FloorEstimatorApplication

class TextResUtil {
    companion object {
        fun getStringFromRes(stringRes: Int, context: Context = FloorEstimatorApplication.appContext): String {
            return context.getString(stringRes)
        }

        fun createUnderlineText(text: String, fromIndex: Int = 0, toIndex: Int = text.length): SpannableString {
            val spannableString = SpannableString(text)
            spannableString.setSpan(UnderlineSpan(), fromIndex, toIndex, 0)
            return spannableString
        }
    }
}