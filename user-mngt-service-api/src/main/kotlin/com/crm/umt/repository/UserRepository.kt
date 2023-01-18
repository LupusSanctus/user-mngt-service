package com.crm.umt.repository

import com.crm.umt.constants.UserAttribute
import com.crm.umt.domain.user.UserEntity
import com.crm.umt.domain.user.UserReducedEntity
import com.crm.umt.repository.db.utils.Order

import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.customizer.Define

import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface UserRepository {
    @SqlUpdate(
        """
        INSERT INTO user (e_mail, first_name, last_name)
        VALUES (:email, :firstName, :lastName)
        """
    )
    @GetGeneratedKeys
    fun insert(@BindBean userEntity: UserReducedEntity): UserEntity

    @SqlQuery(
        """
        SELECT * FROM user
        WHERE id = :userId
        """
    )
    fun findUserById(@Bind("userId") userId: Int): UserEntity

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
        SET e_mail = :email, first_name = :firstName, last_name = :lastName
        WHERE id = :userId
        """
    )
    @GetGeneratedKeys
    fun update(@Bind("userId") userId: Int, @BindBean userEntity: UserReducedEntity): UserEntity

    @SqlUpdate(
        """
        UPDATE user
        SET deleted_at = CURRENT_TIMESTAMP
        WHERE id = :userId
        """
    )
    fun deleteUserById(@Bind("userId") userId: Int)
}