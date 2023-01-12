package com.crm.umt.repository

import com.crm.umt.domain.UserEntity
import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

interface UserRepository {
    fun findAllUsers(): List<UserEntity>
}