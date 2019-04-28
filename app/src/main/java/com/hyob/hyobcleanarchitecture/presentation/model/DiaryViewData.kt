package com.hyob.hyobcleanarchitecture.presentation.model

data class DiaryViewData(
    val id: Long,
    val title: String,
    val content: String,
    var showDiaryDeleteCheckBox: Boolean = false,
    var selectedForDelete: Boolean = false)