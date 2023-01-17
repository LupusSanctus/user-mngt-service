package com.crm.umt.mapper

import com.crm.umt.domain.user.UserEntity
import com.crm.umt.domain.user.UserReducedEntity
import com.crm.umt.dto.user.UserDto
import com.crm.umt.dto.user.UserReducedDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.WARN
)
interface UserMapper {
    fun convertToUserDto(userEntity: UserEntity): UserDto
    fun convertToUser(userDto: UserDto): UserEntity
    fun convertToUserReducedDto(userReducedEntity: UserReducedEntity): UserReducedDto
    fun convertToUserReduced(userReducedDto: UserReducedDto): UserReducedEntity
}