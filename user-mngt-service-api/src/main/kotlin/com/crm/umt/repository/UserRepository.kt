package com.crm.umt.repository

import com.crm.umt.domain.UserEntity

interface UserRepository {
    fun insert(userEntity: UserEntity)

    fun findUserById(userId: Int): UserEntity
    fun findAllUsers(): List<UserEntity>

    fun update(userEntity: UserEntity)
    fun deleteUserById(userId: Int)
}