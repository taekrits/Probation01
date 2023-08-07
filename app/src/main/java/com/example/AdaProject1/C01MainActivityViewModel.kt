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
    val aoC_MockList = listOf(
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
    val oC_State: MutableLiveData<CDownloadData> by lazy {
        MutableLiveData<CDownloadData>(
            CDownloadData(
                tSearchEditText = " ",
                tDateEditText = LocalDate.now().toString(),
                aDataDownloadList = aoC_MockList
            )
        )
    }

    fun C_SETxCheckSyncData(pbCheck: Boolean) {
        if (pbCheck) {
            oC_State.value = oC_State.value?.copy(bShowingOldDataLine = false)
        } else {
            oC_State.value = oC_State.value?.copy(bShowingOldDataLine = true)
        }
    }

    fun C_SETxCheckAllData(pbCheck: Boolean) {

        val aDataList = oC_State.value?.aDataDownloadList
        if (pbCheck) {
            oC_State.value = oC_State.value?.copy(
                aDataDownloadList = aDataList?.map {
                    it.bSelect = true
                    it
                } ?: listOf()
            )
        } else {
            oC_State.value = oC_State.value?.copy(
                aDataDownloadList = aDataList?.map {
                    it.bSelect = false
                    it
                } ?: listOf()
            )
        }
    }

    fun C_SETxSearchData(ptText: String) {
        val aDataList = oC_State.value?.aDataDownloadList

        if (ptText != "") {

            oC_State.value = oC_State.value?.copy(
                aDataDownloadList = aDataList?.filter {
                    it.tName.contains(ptText)
                } ?: listOf()
            )
        } else{
            oC_State.value = oC_State.value?.copy(
                aDataDownloadList = aoC_MockList
            )
        }
    }
}