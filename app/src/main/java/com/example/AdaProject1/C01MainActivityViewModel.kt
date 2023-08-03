package com.example.AdaProject1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.AdaProject1.domain.CDownloadData
import com.example.AdaProject1.domain.CDownloadList
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class C01MainActivityViewModel : ViewModel() {
    val mockList = listOf(
        CDownloadList(
            bSelect = false,
            tName = "บริษัท",
            tDateTime = "2023-08-02 11:44:08"
        ),
        CDownloadList(
            bSelect = false,
            tName = "สาขา",
            tDateTime = "2023-08-02 11:43:04"
        ),
        CDownloadList(
            bSelect = false,
            tName = "ผู้ใช้",
            tDateTime = "2023-08-02 11:41:28"
        ),)
    val state: MutableLiveData<CDownloadData> by lazy {
        MutableLiveData<CDownloadData>(
            CDownloadData(
                tSearchEditText = " ",
                tDateEditText = LocalDate.now().toString(),
                aDataDownloadList = mockList
            )
        )
    }

    fun onCheckSyncData(pbCheck: Boolean) {
        if (pbCheck) {
            state.value = state.value?.copy(bShowingOldDataLine = false)
        } else {
            state.value = state.value?.copy(bShowingOldDataLine = true)
        }
    }

    fun onCheckAllData(pbCheck: Boolean) {

        val dataList = state.value?.aDataDownloadList
        if (pbCheck) {
            state.value = state.value?.copy(
                aDataDownloadList = dataList?.map {
                    it.bSelect = true
                    it
                } ?: listOf()
            )
        } else {
            state.value = state.value?.copy(
                aDataDownloadList = dataList?.map {
                    it.bSelect = false
                    it
                } ?: listOf()
            )
        }
    }

    fun searchData(ptText: String) {
        val dataList = state.value?.aDataDownloadList

        if (ptText != "") {

            state.value = state.value?.copy(
                aDataDownloadList = dataList?.filter {
                    it.tName.contains(ptText)
                } ?: listOf()
            )
        } else{
            state.value = state.value?.copy(
                aDataDownloadList = mockList
            )
        }
    }
}