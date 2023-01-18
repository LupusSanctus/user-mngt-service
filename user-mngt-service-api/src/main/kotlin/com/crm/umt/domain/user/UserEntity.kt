package com.crm.umt.domain.user

import java.time.Instant

data class UserEntity(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,

    val createdAt: Instant,
    val deletedAt: Instant?
)