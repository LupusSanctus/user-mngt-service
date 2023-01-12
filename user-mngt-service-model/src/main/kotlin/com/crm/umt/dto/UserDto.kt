package com.crm.umt.dto

import java.sql.Timestamp
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserDto (
    @NotNull
    val id: Int,
    val email: String,

    @NotBlank
    val firstName: String,

    @NotBlank
    val lastName: String,

    @NotNull
    val createdAt: Timestamp,

    @NotNull
    val deletedAt: Timestamp
)