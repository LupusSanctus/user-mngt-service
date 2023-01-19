package com.crm.umt.repository.exception

object ExceptionConstants {
    const val VIOLATION_CONSTRAINT: String = "23505"
    const val CONNECTION_UNABLE_TO_ESTABLISH: String = "08001"
    const val SQL_SYNTAX_ERROR: String = "42001"
    const val NOT_NULL_COLUMN_VAL: String = "23502"
    const val CHECK_VIOLATION: String = "23514"
}

object MappedErrorMessage {
    const val CHECK_VIOLATION_MESSAGE: String = "The query contains a check violations."
    const val NULL_CHECK_VIOLATION_MESSAGE: String = "The query contains a null-check violations."
    const val SQL_SYNTAX_ERROR_MESSAGE: String = "The SQL query contains syntax errors"
    const val ALREADY_EXISTS_MESSAGE: String = "The user already exists"
    const val UNKNOWN_REASONS_ERROR_MESSAGE: String = "Due to unknown reasons the DB connection was failed."
    const val NOT_EXIST_ERROR: String = "The user was not found."
}