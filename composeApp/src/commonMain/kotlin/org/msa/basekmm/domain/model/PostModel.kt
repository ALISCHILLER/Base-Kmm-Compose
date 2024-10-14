package org.msa.basekmm.domain.model


data class PostModel(
    val id: Long,
    val userId: Int,
    val title: String,
    val body: String
)
