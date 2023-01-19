package com.crm.umt.dto.user

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

import com.crm.umt.constants.UserModelConstants
import com.crm.umt.constants.UserModelConstants.FIRST_NAME
import com.crm.umt.constants.UserModelConstants.LAST_NAME

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

import io.swagger.annotations.ApiModelProperty

@JsonAutoDetect(
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    fieldVisibility = JsonAutoDetect.Visibility.ANY
)
data class UserUpdate(
    @field:NotBlank
    @field:Size(min = 1, max = 100)
    @ApiModelProperty(
        value = UserModelConstants.FIRST_NAME_INFO,
        example = UserModelConstants.FIRST_NAME_USAGE
    )
    @JsonProperty(FIRST_NAME)
    val firstName: String?,

    @field:NotBlank
    @field:Size(min = 1, max = 100)
    @ApiModelProperty(
        value = UserModelConstants.LAST_NAME_INFO,
        example = UserModelConstants.LAST_NAME_USAGE
    )
    @JsonProperty(LAST_NAME)
    val lastName: String?
)