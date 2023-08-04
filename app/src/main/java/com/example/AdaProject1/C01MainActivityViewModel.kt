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
    val aMockList = listOf(
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
    val oState: MutableLiveData<CDownloadData> by lazy {
        MutableLiveData<CDownloadData>(
            CDownloadData(
                tSearchEditText = " ",
                tDateEditText = LocalDate.now().toString(),
                aDataDownloadList = aMockList
            )
        )
    }

    fun C_SETxCheckSyncData(pbCheck: Boolean) {
        if (pbCheck) {
            oState.value = oState.value?.copy(bShowingOldDataLine = false)
        } else {
            oState.value = oState.value?.copy(bShowingOldDataLine = true)
        }
    }

    fun C_SETxCheckAllData(pbCheck: Boolean) {

        val aDataList = oState.value?.aDataDownloadList
        if (pbCheck) {
            oState.value = oState.value?.copy(
                aDataDownloadList = aDataList?.map {
                    it.bSelect = true
                    it
                } ?: listOf()
            )
        } else {
            oState.value = oState.value?.copy(
                aDataDownloadList = aDataList?.map {
                    it.bSelect = false
                    it
                } ?: listOf()
            )
        }
    }

    fun C_SETxSearchData(ptText: String) {
        val aDataList = oState.value?.aDataDownloadList

        if (ptText != "") {

            oState.value = oState.value?.copy(
                aDataDownloadList = aDataList?.filter {
                    it.tName.contains(ptText)
                } ?: listOf()
            )
        } else{
            oState.value = oState.value?.copy(
                aDataDownloadList = aMockList
            )
        }
    }
}