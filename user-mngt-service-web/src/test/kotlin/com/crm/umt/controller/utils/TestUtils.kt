package com.crm.umt.controller.utils

import java.time.Instant
import java.time.ZonedDateTime
import java.util.*

import com.crm.umt.controller.utils.TestConstants.DEFAULT_EMAIL
import com.crm.umt.controller.utils.TestConstants.LOWER_BOUND
import com.crm.umt.controller.utils.TestConstants.MINUTES_MIN_VAL
import com.crm.umt.controller.utils.TestConstants.UPPER_BOUND
import com.crm.umt.domain.user.UserEntity
import com.crm.umt.dto.user.User
import com.crm.umt.dto.user.UserCreate
import com.crm.umt.dto.user.UserUpdate

import org.apache.commons.lang3.RandomStringUtils

object TestConstants {
    const val DEFAULT_EMAIL: String = "@gmail.com"

    const val UPPER_BOUND: Int = 50
    const val LOWER_BOUND: Int = 2

    const val MINUTES_MIN_VAL: Long = 5
}


fun getNearFutureInstant() = ZonedDateTime.now().plusMinutes(MINUTES_MIN_VAL).toInstant()

fun getRandIncludingBounds(startPos: Int, endPos: Int) = Random().nextInt(endPos - startPos) + startPos

fun buildUser(isDeleted: Boolean, email: String = "") = User(
    id = getRandIncludingBounds(LOWER_BOUND, UPPER_BOUND),
    email = RandomStringUtils.randomAlphanumeric(LOWER_BOUND, UPPER_BOUND) + DEFAULT_EMAIL,
    firstName = RandomStringUtils.randomAlphanumeric(LOWER_BOUND, UPPER_BOUND),
    lastName = RandomStringUtils.randomAlphabetic(LOWER_BOUND, UPPER_BOUND),
    createdAt = Instant.now(),
    deletedAt = if (isDeleted) getNearFutureInstant() else null
)

fun buildUserEntity(isDeleted: Boolean, email: String = "") = UserEntity(
    id = getRandIncludingBounds(LOWER_BOUND, UPPER_BOUND),
    email = RandomStringUtils.randomAlphanumeric(LOWER_BOUND, UPPER_BOUND) + DEFAULT_EMAIL,
    firstName = RandomStringUtils.randomAlphanumeric(LOWER_BOUND, UPPER_BOUND),
    lastName = RandomStringUtils.randomAlphabetic(LOWER_BOUND, UPPER_BOUND),
    createdAt = Instant.now(),
    deletedAt = if (isDeleted) getNearFutureInstant() else null
)

fun buildUserCreate() = UserCreate(
    email = RandomStringUtils.randomAlphanumeric(LOWER_BOUND, UPPER_BOUND) + DEFAULT_EMAIL,
    firstName = RandomStringUtils.randomAlphanumeric(LOWER_BOUND, UPPER_BOUND),
    lastName = RandomStringUtils.randomAlphabetic(LOWER_BOUND, UPPER_BOUND)
)

fun buildUserUpdate() = UserUpdate(
    firstName = RandomStringUtils.randomAlphanumeric(LOWER_BOUND, UPPER_BOUND),
    lastName = RandomStringUtils.randomAlphabetic(LOWER_BOUND, UPPER_BOUND),
)
