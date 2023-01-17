package com.crm.umt.domain.user

import java.time.Instant

import com.fasterxml.jackson.annotation.JsonProperty

data class UserReducedEntity(
    @JsonProperty
    val email: String,

    @JsonProperty
    val firstName: String,

    @JsonProperty
    val lastName: String,

    val createdAt: Instant,
    val deletedAt: Instant
)