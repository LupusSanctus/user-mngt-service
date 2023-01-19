package com.crm.umt.controller.validation

object ErrorMessage {
    const val USER_NOT_FOUND: String = "User id = %s not found!"
    const val USER_NOT_FOUND_INFO: String = "User not found!"

    const val USER_DELETED: String = "The user was successfully deleted!"

    const val USER_ALREADY_EXISTS_INFO: String = "The user with that e-mail already exists!"
    const val USER_ALREADY_EXISTS: String = "The user with that e-mail (%s) already exists!"
}