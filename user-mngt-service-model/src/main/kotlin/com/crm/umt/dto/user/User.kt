package com.crm.umt.dto.user

import java.time.Instant

import io.swagger.annotations.ApiModelProperty

import com.crm.umt.constants.UserModelConstants.CREATED_AT_INFO
import com.crm.umt.constants.UserModelConstants.CREATED_AT_USAGE
import com.crm.umt.constants.UserModelConstants.DELETED_AT_INFO
import com.crm.umt.constants.UserModelConstants.DELETED_AT_USAGE
import com.crm.umt.constants.UserModelConstants.EMAIL_INFO
import com.crm.umt.constants.UserModelConstants.EMAIL_USAGE
import com.crm.umt.constants.UserModelConstants.FIRST_NAME_INFO
import com.crm.umt.constants.UserModelConstants.FIRST_NAME_USAGE
import com.crm.umt.constants.UserModelConstants.ID_INFO
import com.crm.umt.constants.UserModelConstants.ID_USAGE
import com.crm.umt.constants.UserModelConstants.LAST_NAME_INFO
import com.crm.umt.constants.UserModelConstants.LAST_NAME_USAGE
import com.fasterxml.jackson.annotation.JsonProperty

data class User(

    @ApiModelProperty(
        value = ID_INFO,
        example = ID_USAGE
    )
    @JsonProperty("id")
    val id: Int,

    @ApiModelProperty(
        value = EMAIL_INFO,
        example = EMAIL_USAGE
    )
    @JsonProperty("email")
    val email: String,

    @ApiModelProperty(
        value = FIRST_NAME_INFO,
        example = FIRST_NAME_USAGE
    )
    @JsonProperty("firstName")
    val firstName: String,

    @ApiModelProperty(
        value = LAST_NAME_INFO,
        example = LAST_NAME_USAGE
    )
    @JsonProperty("lastName")
    val lastName: String,

    @ApiModelProperty(
        value = CREATED_AT_INFO,
        example = CREATED_AT_USAGE
    )
    @JsonProperty("createdAt")
    val createdAt: Instant,

    @ApiModelProperty(
        value = DELETED_AT_INFO,
        example = DELETED_AT_USAGE
    )
    @JsonProperty("deletedAt")
    val deletedAt: Instant?
)