package com.crm.umt.repository.exception

import com.crm.umt.repository.exception.MappedErrorMessage.ALREADY_EXISTS_MESSAGE
import com.crm.umt.repository.exception.MappedErrorMessage.CHECK_VIOLATION_MESSAGE
import com.crm.umt.repository.exception.MappedErrorMessage.NOT_EXIST_ERROR
import com.crm.umt.repository.exception.MappedErrorMessage.NULL_CHECK_VIOLATION_MESSAGE
import com.crm.umt.repository.exception.MappedErrorMessage.SQL_SYNTAX_ERROR_MESSAGE
import com.crm.umt.repository.exception.MappedErrorMessage.UNKNOWN_REASONS_ERROR_MESSAGE
import org.eclipse.jetty.http.HttpStatus


/**
 * Mapper of SQl Exception States and Corresponding Error Codes
 */
open class BaseSqlException(
    val errorCode: Int,
    val errorCodeInfo: String?
) : Exception(errorCodeInfo)

class AlreadyExistsException : BaseSqlException(
    HttpStatus.CONFLICT_409,
    ALREADY_EXISTS_MESSAGE
)

class ConnectionFailedException : BaseSqlException(
    HttpStatus.INTERNAL_SERVER_ERROR_500,
    UNKNOWN_REASONS_ERROR_MESSAGE
)

class NotFoundException : BaseSqlException(
    HttpStatus.NOT_FOUND_404,
    NOT_EXIST_ERROR
)

class CheckViolationException : BaseSqlException(
    HttpStatus.INTERNAL_SERVER_ERROR_500,
    CHECK_VIOLATION_MESSAGE
)

class UnknownReasonException : BaseSqlException(
    HttpStatus.INTERNAL_SERVER_ERROR_500,
    UNKNOWN_REASONS_ERROR_MESSAGE
)

class NullViolationException : BaseSqlException(
    HttpStatus.INTERNAL_SERVER_ERROR_500,
    NULL_CHECK_VIOLATION_MESSAGE
)

class SyntaxErrorException : BaseSqlException(
    HttpStatus.INTERNAL_SERVER_ERROR_500,
    SQL_SYNTAX_ERROR_MESSAGE
)