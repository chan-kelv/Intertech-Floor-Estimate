package com.intertech.floorestimator.ui

import android.os.Bundle
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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
        return super.onCreateOptionsMenu(menu)
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

    private val navListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            Timber.d("id: ${destination.id} with label: ${destination.label} and name: ${destination.navigatorName}")
        }
}