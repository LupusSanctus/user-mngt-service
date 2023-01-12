package com.crm.umt.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserEntity (
    @NotNull
    @JsonProperty
    val id: Int,

    @JsonProperty
    val email: String,

    @NotBlank
    @JsonProperty
    val firstName: String,

    @NotBlank
    @JsonProperty
    val lastName: String,

    @NotNull
    val createdAt: Timestamp,

    @NotNull
    val deletedAt: Timestamp
)