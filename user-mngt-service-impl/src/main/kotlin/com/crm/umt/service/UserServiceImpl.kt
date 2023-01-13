package com.crm.umt.service

import com.crm.umt.dto.UserDto
import java.time.Instant
import com.crm.umt.repository.UserRepository

class UserServiceImpl(
    //private val userRepository: UserRepository,
    // private val userMapper: UserMapper
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

    override fun createUser(userDto: UserDto) {
        // TBD
    }

    override fun findUserById(userId: Int): UserDto {
        return userDtoBlob;
    }

    override fun findAllUsers(): List<UserDto> {
//        // TBD: after DI - uncomment
//        .map { userEntity ->
//            userMapper.convertToUserDto(userEntity)
//        }
        return listOf(userDtoBlob)
    }

    override fun updateUser(userDto: UserDto) {
        // TBD
    }

    override fun deleteUserById(userId: Int) {
        // TBD
    }
}