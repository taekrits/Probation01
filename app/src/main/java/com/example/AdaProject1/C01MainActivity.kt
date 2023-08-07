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

    private val oC_ViewModel: C01MainActivityViewModel by lazy {
        ViewModelProvider(this).get(C01MainActivityViewModel::class.java)
    }
    lateinit var oC_Binding: W01activityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val oCalendar = Calendar.getInstance()
        val oYear = oCalendar.get(Calendar.YEAR)
        val oMonth = oCalendar.get(Calendar.MONTH)
        val oDay = oCalendar.get(Calendar.DAY_OF_MONTH)



        oC_Binding = W01activityMainBinding.inflate(layoutInflater)
        setContentView(oC_Binding.root)

        val oDatePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val tSelectedDate = "$selectedYear-${selectedMonth + 1}-${selectedDay}"
                val oSelectedDateEditable: Editable =
                    Editable.Factory.getInstance().newEditable(tSelectedDate)
                oC_Binding.oet01Date.text = oSelectedDateEditable
            }, oYear, oMonth, oDay)

//            binding.oet01search.setText(it.searchEditText)
        oC_ViewModel.oC_State.observe(this) {

            oC_Binding.oet01Date.setText(it.tDateEditText.toString())
            oC_Binding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            oC_Binding.recyclerView.adapter = C01MainActivityAdapter(it.aDataDownloadList)

            if (it.bShowingOldDataLine) {
                oC_Binding.ocb01OldData.visibility = View.VISIBLE
                oC_Binding.oet01Date.visibility = View.VISIBLE
            } else {
                oC_Binding.ocb01OldData.visibility = View.GONE
                oC_Binding.oet01Date.visibility = View.GONE
            }

        }

        oC_Binding.ocb01SyncData.setOnCheckedChangeListener { compoundButton, pbChecked ->
            oC_ViewModel.C_SETxCheckSyncData(pbChecked)
        }
        oC_Binding.ocb01SelectAllData.setOnCheckedChangeListener { compoundButton, pbChecked ->
            oC_ViewModel.C_SETxCheckAllData(pbChecked)
        }
        oC_Binding.oet01search.doOnTextChanged { text, start, before, count ->
            oC_ViewModel.C_SETxSearchData(text.toString())
        }
        oC_Binding.oet01Date.setOnClickListener {
            oDatePickerDialog.show()
        }
    }
}