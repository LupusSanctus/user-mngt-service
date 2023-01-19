package com.crm.umt.service

import java.time.Instant
import com.crm.umt.constants.UserAttribute
import com.crm.umt.domain.user.UserEntity
import com.crm.umt.dto.user.User
import com.crm.umt.dto.user.UserCreate
import com.crm.umt.dto.user.UserUpdate
import com.crm.umt.mapper.UserMapper
import com.crm.umt.repository.UserRepository
import com.crm.umt.repository.db.utils.Order

class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserService {

    override fun createUser(userReducedDto: UserCreate): User {
        return userMapper.convertToUserDto(userRepository.insert(userMapper.convertToUserCreate(userReducedDto)))
    }

    override fun findUserById(userId: Int): User? {
        return userMapper.convertToNullableUserDto(userRepository.findUserById(userId))
    }

    override fun findUserByEmail(email: String): UserEntity? {
        return userRepository.findUserByEmail(email)
    }

    override fun findAllUsers(
        isNull: Boolean,
        userAttribute: UserAttribute,
        orderType: Order,
        numberOfUsers: Int,
        offsetUserNumber: Int
    ): List<User> {
        return userRepository.findAllUsers(
            isNull,
            userAttribute,
            orderType,
            numberOfUsers,
            offsetUserNumber
        ).map { userEntity ->
            userMapper.convertToUserDto(userEntity)
        }
    }

    override fun updateUser(userId: Int, userUpdateDto: UserUpdate): User? {
        return userMapper.convertToUserDto(
            userRepository.update(
                userId,
                userMapper.convertToUserUpdate(userUpdateDto)
            )
        )
    }

    override fun deleteUserById(userId: Int) {
        userRepository.deleteUserById(userId)
    }
}