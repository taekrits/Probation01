package com.example.AdaProject1.domain

data class CDownloadData(
    val tSearchEditText: String = "",
    val bSelectAll: Boolean = false,
    val bSyncData: Boolean = false,
    val bClearOldData: Boolean = false,
    val tDateEditText: String = "",
    val aDataDownloadList: List<CDownloadList> = listOf(),
    val bShowingOldDataLine: Boolean = true
)

data class CDownloadList(
    var bSelect: Boolean,
    val tName: String,
    val tDateTime: String
)
