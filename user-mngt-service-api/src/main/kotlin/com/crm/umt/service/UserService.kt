package com.crm.umt.service

import com.crm.umt.constants.UserAttribute
import com.crm.umt.domain.user.UserEntity
import com.crm.umt.dto.user.User
import com.crm.umt.dto.user.UserCreate
import com.crm.umt.dto.user.UserUpdate
import com.crm.umt.repository.db.utils.Order
import org.jdbi.v3.sqlobject.customizer.Bind

interface UserService {
    fun createUser(userDto: UserCreate): User
    fun findUserById(userId: Int): User?
    fun findUserByEmail(email: String): UserEntity?
    fun findAllUsers(
        isNull: Boolean,
        userAttribute: UserAttribute,
        orderType: Order,
        numberOfUsers: Int,
        offsetUserNumber: Int
    ): List<User>
    fun updateUser(userId: Int, userUpdateDto: UserUpdate): User?
    fun deleteUserById(userId: Int): User
}