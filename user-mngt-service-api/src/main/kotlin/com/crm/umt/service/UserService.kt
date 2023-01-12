package com.crm.umt.service

import com.crm.umt.dto.UserDto
interface UserService {
    fun findAllUsers(): List<UserDto>
}