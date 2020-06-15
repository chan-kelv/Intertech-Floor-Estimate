package com.intertech.floorestimator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.intertech.floorestimator.R
import kotlinx.android.synthetic.main.fragment_estimate_customer_info.*

class EstimateCustomerInfo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estimate_customer_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bttn_custInfo_morePhone.setOnClickListener { addPhoneRow() }
    }

    private fun addPhoneRow() {
        if (row_custInfo_phone2.visibility == View.GONE) {
            activity?.runOnUiThread { row_custInfo_phone2.visibility = View.VISIBLE }
        } else if (row_custInfo_phone3.visibility == View.GONE) {
            activity?.runOnUiThread {
                row_custInfo_phone3.visibility = View.VISIBLE
                bttn_custInfo_morePhone.visibility = View.GONE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //TODO can be interface listener pattern
        (activity as NewEstimateActivity).setToolbarTitle("Customer Info")
    }

    fun navigateToServiceArea() {
        findNavController().navigate(R.id.action_estimateCustomerInfo_to_estimateServiceArea)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EstimateCustomerInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            EstimateCustomerInfo().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}