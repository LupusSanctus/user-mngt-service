package com.crm.umt.domain

import java.time.Instant

import com.fasterxml.jackson.annotation.JsonProperty

data class UserEntity(
    @JsonProperty
    val id: Int,

    @JsonProperty
    val email: String,

    @JsonProperty
    val firstName: String,

    @JsonProperty
    val lastName: String,

    val createdAt: Instant,
    val deletedAt: Instant
)