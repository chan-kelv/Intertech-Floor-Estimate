package com.intertech.floorestimator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intertech.floorestimator.R

class EstimateServiceArea : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estimate_service_area, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as NewEstimateActivity).setToolbarTitle("Service Area")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EstimateServiceArea().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}