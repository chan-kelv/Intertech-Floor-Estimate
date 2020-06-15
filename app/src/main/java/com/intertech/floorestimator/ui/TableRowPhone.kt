package com.intertech.floorestimator.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TableRow
import com.intertech.floorestimator.R

class TableRowPhone(context: Context, attrs: AttributeSet) : TableRow(context, attrs) {
    init {
        View.inflate(context, R.layout.tablerow_phone, this)
    }
}