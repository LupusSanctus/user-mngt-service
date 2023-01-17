package com.crm.umt.service

import java.sql.SQLDataException
import java.time.Instant
import com.crm.umt.constants.UserAttribute
import com.crm.umt.dto.user.UserDto
import com.crm.umt.dto.user.UserReducedDto
import com.crm.umt.mapper.UserMapper
import com.crm.umt.repository.UserRepository
import com.crm.umt.repository.db.utils.Order

class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserService {
    // TBD: move to test. Use repository implementation.
    companion object {
        val userDtoBlob = UserDto(
            id = 1,
            email = "test@gmail.com",
            firstName = "John",
            lastName = "Doe",
            createdAt = Instant.now(),
            deletedAt = Instant.now()
        )
    }

    override fun createUser(userDto: UserReducedDto): UserDto {
        return userDtoBlob;
    }

    override fun findUserById(userId: Int): UserDto {
        return userDtoBlob;
    }

    override fun findAllUsers(
        isNull: String,
        userAttribute: UserAttribute,
        orderType: Order,
        numberOfUsers: Int,
        offsetUserNumber: Int
    ): List<UserDto> {
        return userRepository.findAllUsers(
            isNull,
            userAttribute,
            orderType,
            numberOfUsers,
            offsetUserNumber
        ).map { userEntity -> userMapper.convertToUserDto(userEntity) }
    }

    override fun updateUser(userDto: UserReducedDto): UserDto? {
        return userDtoBlob;
    }

    override fun deleteUserById(userId: Int) {
        userRepository.deleteUserById(userId)
    }
}