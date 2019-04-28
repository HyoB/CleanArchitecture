package com.hyob.hyobcleanarchitecture.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DIARY_CONTENT_TABLE_NAME)
data class DiaryDto(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = DIARY_TITLE_COLUMN_NAME) var title: String,
    @ColumnInfo(name = DIARY_CONTENT_COLUMN_NAME) var content: String
)