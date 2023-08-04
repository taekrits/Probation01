package com.example.AdaProject1

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adap1.databinding.W01activityMainBinding
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
class C01MainActivity : AppCompatActivity() {

    private val oViewModel: C01MainActivityViewModel by lazy {
        ViewModelProvider(this).get(C01MainActivityViewModel::class.java)
    }
    lateinit var oBinding: W01activityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val oCalendar = Calendar.getInstance()
        val oYear = oCalendar.get(Calendar.YEAR)
        val oMonth = oCalendar.get(Calendar.MONTH)
        val oDay = oCalendar.get(Calendar.DAY_OF_MONTH)



        oBinding = W01activityMainBinding.inflate(layoutInflater)
        setContentView(oBinding.root)

        val oDatePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val tSelectedDate = "$selectedYear-${selectedMonth + 1}-${selectedDay}"
                val oSelectedDateEditable: Editable =
                    Editable.Factory.getInstance().newEditable(tSelectedDate)
                oBinding.oet01Date.text = oSelectedDateEditable
            }, oYear, oMonth, oDay)

//            binding.oet01search.setText(it.searchEditText)
        oViewModel.oState.observe(this) {

            oBinding.oet01Date.setText(it.tDateEditText.toString())
            oBinding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            oBinding.recyclerView.adapter = C01MainActivityAdapter(it.aDataDownloadList)

            if (it.bShowingOldDataLine) {
                oBinding.ocb01OldData.visibility = View.VISIBLE
                oBinding.oet01Date.visibility = View.VISIBLE
            } else {
                oBinding.ocb01OldData.visibility = View.GONE
                oBinding.oet01Date.visibility = View.GONE
            }

        }

        oBinding.ocb01SyncData.setOnCheckedChangeListener { compoundButton, pbChecked ->
            oViewModel.C_SETxCheckSyncData(pbChecked)
        }
        oBinding.ocb01SelectAllData.setOnCheckedChangeListener { compoundButton, pbChecked ->
            oViewModel.C_SETxCheckAllData(pbChecked)
        }
        oBinding.oet01search.doOnTextChanged { text, start, before, count ->
            oViewModel.C_SETxSearchData(text.toString())
        }
        oBinding.oet01Date.setOnClickListener {
            oDatePickerDialog.show()
        }
    }
}