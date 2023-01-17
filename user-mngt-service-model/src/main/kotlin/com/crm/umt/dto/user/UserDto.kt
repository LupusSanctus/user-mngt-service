package com.crm.umt.dto.user

import java.time.Instant
import javax.validation.constraints.Size
import io.swagger.annotations.ApiModelProperty

data class UserDto(
    @ApiModelProperty(value="User ID")
    val id: Int,

    @field:Size(max = 150)
    @ApiModelProperty(value="User e-mail address")
    val email: String,

    @field:Size(min = 1, max = 100)
    @ApiModelProperty(value="User First Name")
    val firstName: String,

    @field:Size(min = 1, max = 100)
    @ApiModelProperty(value="User Last Name")
    val lastName: String,

    @ApiModelProperty(value="The timestamp of user creation")
    val createdAt: Instant,

    @ApiModelProperty(value="The timestamp of user deletion")
    val deletedAt: Instant
)