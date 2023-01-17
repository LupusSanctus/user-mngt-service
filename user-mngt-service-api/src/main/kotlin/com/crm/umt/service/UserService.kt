package com.crm.umt.service

import com.crm.umt.constants.UserAttribute
import com.crm.umt.dto.user.UserDto
import com.crm.umt.dto.user.UserReducedDto
import com.crm.umt.repository.db.utils.Order

interface UserService {
    fun createUser(userDto: UserReducedDto): UserDto
    fun findUserById(userId: Int): UserDto
    fun findAllUsers(
        isNull: String,
        userAttribute: UserAttribute,
        orderType: Order,
        numberOfUsers: Int,
        offsetUserNumber: Int
    ): List<UserDto>
    fun updateUser(userDto: UserReducedDto): UserDto?
    fun deleteUserById(userId: Int)
}