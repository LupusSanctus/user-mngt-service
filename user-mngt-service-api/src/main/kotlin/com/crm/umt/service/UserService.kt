package com.crm.umt.service

import com.crm.umt.dto.UserDto

interface UserService {
    fun createUser(userDto: UserDto)

    fun findUserById(userId: Int): UserDto
    fun findAllUsers(): List<UserDto>

    fun updateUser(userDto: UserDto)
    fun deleteUserById(userId: Int)
}