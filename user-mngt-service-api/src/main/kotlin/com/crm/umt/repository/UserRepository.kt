package com.crm.umt.repository

import com.crm.umt.constants.EntityAttributeList.DELETED_AT
import com.crm.umt.constants.EntityAttributeList.FIRST_NAME
import com.crm.umt.constants.EntityAttributeList.LAST_NAME
import com.crm.umt.constants.EntityAttributeList.EMAIL_ADDRESS
import com.crm.umt.constants.EntityAttributeList.ID
import com.crm.umt.constants.UserAttribute
import com.crm.umt.constants.UserMngtServiceTables.User.USER
import com.crm.umt.domain.user.UserEntity
import com.crm.umt.domain.user.UserReducedEntity
import com.crm.umt.repository.db.utils.Order
import com.crm.umt.repository.db.utils.SqlConstants.INSERT_INTO
import com.crm.umt.repository.db.utils.SqlConstants.IS
import com.crm.umt.repository.db.utils.SqlConstants.LIMIT
import com.crm.umt.repository.db.utils.SqlConstants.OFFSET
import com.crm.umt.repository.db.utils.SqlConstants.ORDER_BY
import com.crm.umt.repository.db.utils.SqlConstants.SELECT_ALL_FROM
import com.crm.umt.repository.db.utils.SqlConstants.SET
import com.crm.umt.repository.db.utils.SqlConstants.UPDATE
import com.crm.umt.repository.db.utils.SqlConstants.VALUES
import com.crm.umt.repository.db.utils.SqlConstants.WHERE
import com.fasterxml.jackson.annotation.JsonProperty

import org.jdbi.v3.sqlobject.customizer.Define
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface UserRepository {

    @SqlUpdate(
        "$INSERT_INTO $USER ($FIRST_NAME, $LAST_NAME, $EMAIL_ADDRESS)" +
        "$VALUES(:userEntity.email, :userEntity.firstName, :userEntity.lastName)"
    )
    @GetGeneratedKeys
    fun insert(userEntity: UserReducedEntity): UserEntity

    @SqlQuery("$SELECT_ALL_FROM $USER $WHERE $ID = ?")
    fun findUserById(userId: Int): UserEntity?

    @SqlQuery(
        "$SELECT_ALL_FROM $USER " +
        "$WHERE $DELETED_AT $IS :isNull " +
        "$ORDER_BY <ATTRIBUTE> <ORDER> " +
        "$LIMIT :numberOfUsers $OFFSET offsetUserNumber"
    )
    fun findAllUsers(
        isNull: String,
        @Define("ATTRIBUTE") userAttribute: UserAttribute,
        @Define("ORDER") orderType: Order,
        numberOfUsers: Int,
        offsetUserNumber: Int
    ): List<UserEntity>
    @SqlUpdate(
        "$UPDATE $USER $SET " +
        "E_MAIL = :userEntity.email, " +
        "FIRST_NAME = :userEntity.firstName, " +
        "LAST_NAME = :userEntity.lastName"
    )
    @GetGeneratedKeys
    fun update(userEntity: UserReducedEntity) : UserEntity?

    @SqlUpdate(
        "$UPDATE $USER " +
        "$SET <UserAttribute.DELETED_AT>  = CURRENT_TIMESTAMP " +
        "$WHERE <UserAttribute.ID> = ?"
    )
    fun deleteUserById(userId: Int)
}