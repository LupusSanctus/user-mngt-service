package com.crm.umt.service

import com.crm.umt.domain.UserEntity
import com.crm.umt.dto.UserDto
import com.crm.umt.mapper.UserMapper
import com.crm.umt.repository.UserRepository
import com.crm.umt.service.UserService
import java.sql.Timestamp

class UserServiceImpl(
    // private val userRepository: UserRepository,
    // private val userMapper: UserMapper
) : UserService {
    override fun findAllUsers(): List<UserDto> {
        // TBD: move to test. Use repository implementation.
        val userList = listOf(
            UserDto(
                1,                // id
                "test@gmail.com", // e-mail
                "John",           // name
                "Doe",            // last name
                Timestamp(System.currentTimeMillis()), // createdAt
                Timestamp(System.currentTimeMillis()) // deletedAt
            )
        )
//        // TBD: after DI - uncomment
//        .map { userEntity ->
//            userMapper.convertToUserDto(userEntity)
//        }
        return userList
    }
}