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
    lateinit var binding: W01activityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val oCalendar = Calendar.getInstance()
        val oYear = oCalendar.get(Calendar.YEAR)
        val oMonth = oCalendar.get(Calendar.MONTH)
        val oDay = oCalendar.get(Calendar.DAY_OF_MONTH)



        binding = W01activityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val oDatePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val tSelectedDate = "$selectedYear-${selectedMonth + 1}-${selectedDay}"
                val oSelectedDateEditable: Editable =
                    Editable.Factory.getInstance().newEditable(tSelectedDate)
                binding.oet01Date.text = oSelectedDateEditable
            }, oYear, oMonth, oDay)

//            binding.oet01search.setText(it.searchEditText)
        oViewModel.oState.observe(this) {

            binding.oet01Date.setText(it.tDateEditText.toString())
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = C01MainActivityAdapter(it.aDataDownloadList)

            if (it.bShowingOldDataLine) {
                binding.ocb01OldData.visibility = View.VISIBLE
                binding.oet01Date.visibility = View.VISIBLE
            } else {
                binding.ocb01OldData.visibility = View.GONE
                binding.oet01Date.visibility = View.GONE
            }

        }

        binding.ocb01SyncData.setOnCheckedChangeListener { compoundButton, pbChecked ->
            oViewModel.C_SETxCheckSyncData(pbChecked)
        }
        binding.ocb01SelectAllData.setOnCheckedChangeListener { compoundButton, pbChecked ->
            oViewModel.C_SETxCheckAllData(pbChecked)
        }
        binding.oet01search.doOnTextChanged { text, start, before, count ->
            oViewModel.C_SETxSearchData(text.toString())
        }
        binding.oet01Date.setOnClickListener {
            oDatePickerDialog.show()
        }
    }
}