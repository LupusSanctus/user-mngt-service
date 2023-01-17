package com.crm.umt.repository.db.utils

object SqlConstants {
    const val SELECT: String = "SELECT"
    const val UPDATE: String = "UPDATE"
    const val SET: String = "SET"
    const val INSERT_INTO: String = "INSERT INTO"

    const val WHERE: String = "WHERE"
    const val FROM: String = "FROM"
    const val IS: String = "IS"

    const val ORDER_BY: String = "ORDER BY"
    const val LIMIT: String = "LIMIT"
    const val OFFSET: String = "OFFSET"
    const val VALUES: String = "VALUES"

    const val NULL: String = "NULL"
    const val NOT_NULL: String = "NOT $NULL"

    const val SELECT_ALL_FROM: String = "$SELECT * $FROM"
}