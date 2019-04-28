package com.hyob.hyobcleanarchitecture.domain

import java.lang.RuntimeException

class DiaryNullPointException(val type: DiaryNullType) : RuntimeException("DiaryTitle or DiaryContent is null")

enum class DiaryNullType(val reason: String) {
    TITLE("제목을 입력하세요."),
    CONTENT("내용을 입력하세요.")
}