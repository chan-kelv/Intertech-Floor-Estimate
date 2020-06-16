package com.intertech.floorestimator.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.intertech.floorestimator.R
import kotlinx.android.synthetic.main.activity_new_estimate.*
import timber.log.Timber

class NewEstimateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_estimate)
        setSupportActionBar(toolbar_estimate)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_start, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var consumed = false
        when(item.itemId) {
            R.id.item_startMenu_start -> {
                navigateToServiceArea()
                consumed = true
            }
            else -> consumed = super.onOptionsItemSelected(item)
        }
        return consumed
    }

    override fun onResume() {
        super.onResume()
        findNavController(R.id.navFrag_estimate_container).addOnDestinationChangedListener(navListener)
    }

    override fun onPause() {
        super.onPause()
        findNavController(R.id.navFrag_estimate_container).removeOnDestinationChangedListener(navListener)
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun navigateToServiceArea() {
        findNavController(R.id.navFrag_estimate_container).navigate(R.id.action_estimateCustomerInfo_to_estimateServiceArea)
    }

    private val navListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            Timber.d("id: ${destination.id} with label: ${destination.label} and name: ${destination.navigatorName}")
        }
}