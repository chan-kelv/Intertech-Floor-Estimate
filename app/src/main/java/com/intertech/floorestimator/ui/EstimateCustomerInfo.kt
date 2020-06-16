package com.intertech.floorestimator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.intertech.floorestimator.R
import com.intertech.floorestimator.model.CustomerInfo
import com.intertech.floorestimator.model.NewEstimateVM
import kotlinx.android.synthetic.main.fragment_estimate_customer_info.*
import kotlinx.android.synthetic.main.tablerow_phone.*
import kotlinx.android.synthetic.main.tablerow_phone.view.*

class EstimateCustomerInfo : Fragment() {
    var newEstimateVm: NewEstimateVM? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_estimate_customer_info, container, false)
        setHasOptionsMenu(true);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newEstimateVm = activity?.run {
            ViewModelProvider(this, NewEstimateVM.NewEstimateVmFactory())
                .get(NewEstimateVM::class.java)
        }

        bttn_custInfo_morePhone.setOnClickListener { addPhoneRow() }
    }

    override fun onStart() {
        super.onStart()
        //TODO can be interface listener pattern
        (activity as NewEstimateActivity).setToolbarTitle("Customer Info")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var consumed = false
        when(item.itemId) {
            R.id.item_startMenu_start -> {
                setCustomerInfo()
                consumed = true
            }
            else -> consumed = super.onOptionsItemSelected(item)
        }
        return consumed
    }

    private fun setCustomerInfo() {
        createCustomerInfo().takeIf { it != null }?.apply {
            newEstimateVm?.setCustomerInfo(this)
            navigateToServiceArea()
        } ?: run {
            Toast.makeText(activity, "Need valid customer info", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createCustomerInfo(): CustomerInfo? {
        // Date
        val date = if (input_custInfo_date.text.isNotBlank()) {
            input_custInfo_date.text.toString()
        } else {
            input_custInfo_date.error = "Date is needed"
            null
        }

        // Full Name
        val fullName = if (input_custInfo_name.text.isNotBlank()) {
            input_custInfo_name.text.toString()
        } else {
            input_custInfo_name.error = "Full Name is needed"
            null
        }

        // Address
        val address = if (input_custInfo_address.text.isNotBlank()) {
            input_custInfo_address.text.toString()
        } else {
            input_custInfo_address.error = "Address is needed"
            null
        }

        // Postal Code
        val postalCode = if (input_custInfo_postal.text.isNotBlank()) {
            input_custInfo_postal.text.toString()
        } else {
            input_custInfo_postal.error = "Postal Code is needed"
            null
        }

        // First Phone
        val phone1 : CustomerInfo.PhoneNumber? = if (CustomerInfo.PhoneNumber
                .isValidPhone(row_custInfo_phone.input_custInfo_phoneNo.text.toString())){
            CustomerInfo.PhoneNumber(
                row_custInfo_phone.input_custInfo_phoneNo.text.toString(),
                row_custInfo_phone.input_custInfo_phoneExt.text.toString(),
                row_custInfo_phone.spinner_custInfo_phoneType.selectedItem.toString()
                )
        } else {
            null
        }

        // Second Phone
        val phone2 : CustomerInfo.PhoneNumber? = if (CustomerInfo.PhoneNumber
                .isValidPhone(row_custInfo_phone2.input_custInfo_phoneNo.text.toString())){
            CustomerInfo.PhoneNumber(
                row_custInfo_phone2.input_custInfo_phoneNo.text.toString(),
                row_custInfo_phone2.input_custInfo_phoneExt.text.toString(),
                row_custInfo_phone2.spinner_custInfo_phoneType.selectedItem.toString()
            )
        } else {
            null
        }

        // Third Phone
        val phone3 : CustomerInfo.PhoneNumber? = if (CustomerInfo.PhoneNumber
                .isValidPhone(row_custInfo_phone3.input_custInfo_phoneNo.text.toString())){
            CustomerInfo.PhoneNumber(
                row_custInfo_phone3.input_custInfo_phoneNo.text.toString(),
                row_custInfo_phone3.input_custInfo_phoneExt.text.toString(),
                row_custInfo_phone3.spinner_custInfo_phoneType.selectedItem.toString()
            )
        } else {
            null
        }

        // Ensure at least 1 phone number is valid
        if (phone1 == null && phone2 == null && phone3 == null) {
            row_custInfo_phone.input_custInfo_phoneNo.error = "One valid phone number is needed"
        }

        // attention name (optional)
        val attentionName = if (input_custInfo_attnName.text.isNotBlank()) {
            input_custInfo_attnName.text.toString()
        } else {
            null
        }

        // create valid Customer Info
        return CustomerInfo.createValidCustomerInfo(date, fullName, address, postalCode, phone1, phone2, phone3, attentionName)
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

    private fun navigateToServiceArea() {
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