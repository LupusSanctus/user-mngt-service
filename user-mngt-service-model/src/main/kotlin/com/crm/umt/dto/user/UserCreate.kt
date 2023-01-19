package com.crm.umt.dto.user

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

import io.swagger.annotations.ApiModelProperty

import com.crm.umt.constants.UserModelConstants
import com.crm.umt.constants.UserModelConstants.EMAIL
import com.crm.umt.constants.UserModelConstants.FIRST_NAME
import com.crm.umt.constants.UserModelConstants.LAST_NAME

@JsonAutoDetect(
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    fieldVisibility = JsonAutoDetect.Visibility.ANY
)
data class UserCreate(
    @field:Email
    @field:NotBlank
    @ApiModelProperty(
        value = UserModelConstants.EMAIL_INFO,
        example = UserModelConstants.EMAIL_USAGE
    )
    @JsonProperty(EMAIL)
    val email: String,

    @field:Size(min = 1, max = 100)
    @field:NotBlank
    @ApiModelProperty(
        value = UserModelConstants.FIRST_NAME_INFO,
        example = UserModelConstants.FIRST_NAME_USAGE
    )
    @JsonProperty(FIRST_NAME)
    val firstName: String,

    @field:Size(min = 1, max = 100)
    @field:NotBlank
    @ApiModelProperty(
        value = UserModelConstants.LAST_NAME_INFO,
        example = UserModelConstants.LAST_NAME_USAGE
    )
    @JsonProperty(LAST_NAME)
    val lastName: String
)