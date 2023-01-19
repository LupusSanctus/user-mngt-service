package com.crm.umt.repository.exception

import java.sql.SQLException

import com.crm.umt.repository.exception.ExceptionConstants.CHECK_VIOLATION
import com.crm.umt.repository.exception.ExceptionConstants.CONNECTION_UNABLE_TO_ESTABLISH
import com.crm.umt.repository.exception.ExceptionConstants.NOT_NULL_COLUMN_VAL
import com.crm.umt.repository.exception.ExceptionConstants.SQL_SYNTAX_ERROR
import com.crm.umt.repository.exception.ExceptionConstants.VIOLATION_CONSTRAINT

import org.jdbi.v3.core.JdbiException

val sqlStateToExceptionMap: HashMap<String, Exception> = hashMapOf(
    VIOLATION_CONSTRAINT to AlreadyExistsException(),
    CONNECTION_UNABLE_TO_ESTABLISH to ConnectionFailedException(),
    CHECK_VIOLATION to CheckViolationException(),
    SQL_SYNTAX_ERROR to SyntaxErrorException(),
    NOT_NULL_COLUMN_VAL to NullViolationException()
)

inline fun <reified T> handleSqlException(repoFunc: () -> (T?)): T {
    try {
        return repoFunc() ?: throw NotFoundException()
    } catch (jdbiException: JdbiException) {
        val exceptionInfo = sqlStateToExceptionMap[
            (jdbiException.cause as SQLException).sqlState
        ]
        throw (exceptionInfo ?: UnknownReasonException())
    }
}