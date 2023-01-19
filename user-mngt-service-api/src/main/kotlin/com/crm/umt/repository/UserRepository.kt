package com.crm.umt.repository

import com.crm.umt.constants.UserAttribute
import com.crm.umt.domain.user.UserCreateEntity
import com.crm.umt.domain.user.UserEntity
import com.crm.umt.domain.user.UserUpdateEntity
import com.crm.umt.repository.db.utils.Order

import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.Define
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface UserRepository {
    @SqlUpdate(
        """
        INSERT INTO user (e_mail, first_name, last_name, created_at)
        VALUES (:userEntity.email, :userEntity.firstName, :userEntity.lastName, CURRENT_TIMESTAMP)
        """
    )
    @GetGeneratedKeys("id", "e_mail", "first_name", "last_name", "created_at", "deleted_at")
    fun insert(userEntity: UserCreateEntity): UserEntity

    @SqlQuery(
        """
        SELECT * FROM user
        WHERE id = :userId
        """
    )
    fun findUserById(@Bind("userId") userId: Int): UserEntity?

    @SqlQuery(
        """
        SELECT * FROM user
        WHERE e_mail = :email
        """
    )
    fun findUserByEmail(@Bind("email") email: String): UserEntity?

    @SqlQuery(
        """
        SELECT * FROM user
        WHERE (deleted_at IS NOT NULL) = :isNull
        ORDER BY <ATTRIBUTE> <ORDER>
        LIMIT :numberOfUsers OFFSET :offsetUserNumber
        """
    )
    fun findAllUsers(
        @Bind("isNull") isNull: Boolean,
        @Define("ATTRIBUTE") userAttribute: UserAttribute,
        @Define("ORDER") orderType: Order,
        numberOfUsers: Int,
        offsetUserNumber: Int
    ): List<UserEntity>

    @SqlUpdate(
        """
        UPDATE user
        SET first_name = coalesce(:userEntity.firstName, first_name), last_name = coalesce(:userEntity.lastName, last_name)
        WHERE id = :userId
        """
    )
    @GetGeneratedKeys("id", "e_mail", "first_name", "last_name", "created_at", "deleted_at")
    fun update(@Bind("userId") userId: Int, userEntity: UserUpdateEntity): UserEntity

    @SqlUpdate(
        """
        UPDATE user
        SET deleted_at = CURRENT_TIMESTAMP
        WHERE id = :userId
        """
    )
    @GetGeneratedKeys("id", "e_mail", "first_name", "last_name", "created_at", "deleted_at")
    fun deleteUserById(@Bind("userId") userId: Int): UserEntity
}