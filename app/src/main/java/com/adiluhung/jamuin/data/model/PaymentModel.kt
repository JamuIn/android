package com.adiluhung.jamuin.data.model

data class ContentModel(
    val Id: Int,
    val Title: String,
    val Sections: List<SectionModel>
)

data class SectionModel(
    val Id: Int,
    val Title: String,
    val ImageResId: Int
)

