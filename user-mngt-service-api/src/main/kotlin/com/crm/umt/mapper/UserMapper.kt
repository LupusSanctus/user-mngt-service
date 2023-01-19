package com.crm.umt.mapper

import com.crm.umt.domain.user.UserCreateEntity
import com.crm.umt.domain.user.UserEntity
import com.crm.umt.domain.user.UserUpdateEntity
import com.crm.umt.dto.user.User
import com.crm.umt.dto.user.UserCreate
import com.crm.umt.dto.user.UserUpdate

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface UserMapper {
    fun convertToUserDto(userEntity: UserEntity): User
    fun convertToNullableUserDto(userEntity: UserEntity?): User?

    fun convertToUser(userDto: User): UserEntity

    fun convertToUserCreateDto(userCreateEntity: UserCreateEntity): UserCreate
    fun convertToUserCreate(userCreateDto: UserCreate): UserCreateEntity

    fun convertToUserUpdateDto(userUpdateEntity: UserUpdateEntity): UserUpdate
    fun convertToUserUpdate(userUpdateDto: UserUpdate): UserUpdateEntity
}