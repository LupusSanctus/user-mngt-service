package com.crm.umt.dto

import java.time.Instant

data class UserDto(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val createdAt: Instant,
    val deletedAt: Instant
)