package com.hyob.hyobcleanarchitecture.domain

import com.hyob.hyobcleanarchitecture.base.EntityMapper
import com.hyob.hyobcleanarchitecture.data.db.DiaryDto
import com.hyob.hyobcleanarchitecture.entity.Diary
import com.hyob.hyobcleanarchitecture.presentation.model.DiaryViewData

object DiaryEntityMapper : EntityMapper<DiaryDto, Diary, DiaryViewData> {

    override fun mapDataToEntity(data: DiaryDto): Diary = with(data) {
        Diary(id!!, title, content)
    }

    override fun mapEntityToData(entity: Diary): DiaryDto = with(entity) {
        return DiaryDto(id, title, content)
    }

    override fun mapViewToData(view: DiaryViewData): DiaryDto = with(view) {
        return DiaryDto(id, title, content)
    }

    override fun mapEntityToView(entity: Diary): DiaryViewData = with(entity) {
        return DiaryViewData(id, title, content)
    }
}