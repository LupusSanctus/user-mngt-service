package com.crm.umt.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

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