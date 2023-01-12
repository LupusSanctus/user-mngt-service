package com.crm.umt.mapper

import com.crm.umt.domain.UserEntity
import com.crm.umt.dto.UserDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.WARN
)
interface UserMapper {
    fun convertToUserDto(userEntity: UserEntity): UserDto
    fun convertToUser(userDto: UserDto): UserEntity
}