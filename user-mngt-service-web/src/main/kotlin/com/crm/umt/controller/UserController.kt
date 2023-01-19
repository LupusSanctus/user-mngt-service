package com.crm.umt.controller

import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import java.lang.String.format

import com.crm.umt.constants.UserAttribute
import com.crm.umt.controller.validation.ErrorMessage.USER_ALREADY_EXISTS
import com.crm.umt.controller.validation.ErrorMessage.USER_ALREADY_EXISTS_INFO
import com.crm.umt.controller.validation.ErrorMessage.USER_DELETED
import com.crm.umt.controller.validation.ErrorMessage.USER_NOT_FOUND
import com.crm.umt.controller.validation.ErrorMessage.USER_NOT_FOUND_INFO
import com.crm.umt.dto.user.User
import com.crm.umt.dto.user.UserCreate
import com.crm.umt.dto.user.UserUpdate
import com.crm.umt.repository.db.utils.Order
import com.crm.umt.service.UserService

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

import org.eclipse.jetty.http.HttpStatus

@Api(tags = ["Users"])
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
class UserController(private val userService: UserService) {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create new user with specified values")
    @ApiResponses(
        ApiResponse(code = HttpStatus.CREATED_201, message = "OK"),
        ApiResponse(code = HttpStatus.CONFLICT_409, message = USER_ALREADY_EXISTS_INFO)
    )
    fun createUser(
        @Valid
        @ApiParam("User data") userCreateDto: UserCreate
    ): Response {
        if (userService.findUserByEmail(userCreateDto.email) != null) {
            throw WebApplicationException(format(USER_ALREADY_EXISTS, userCreateDto.email), Response.Status.CONFLICT)
        }
        return Response.status(HttpStatus.CREATED_201)
            .entity(userService.createUser(userCreateDto))
            .build()
    }

    @GET
    @Path("/{id}")
    @ApiResponses(
        ApiResponse(code = HttpStatus.OK_200, message = "OK"),
        ApiResponse(code = HttpStatus.NOT_FOUND_404, message = USER_NOT_FOUND_INFO)
    )
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Find the user by specified user id")
    fun findUserById(
        @ApiParam("User ID", required = true)
        @PathParam("id") userId: Int
    ): User {
        return userService.findUserById(userId) ?: throw WebApplicationException(format(USER_NOT_FOUND, userId), HttpStatus.NOT_FOUND_404)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve the users modifying the list with set parameters")
    @ApiResponses(ApiResponse(code = HttpStatus.OK_200, message = "OK"))
    fun findAllUsers(
        @ApiParam("Maximum number of users to return") @QueryParam("limit") @Min(1) @Max(100) @DefaultValue("25") limit: Int,
        @ApiParam("The number of the users to skip") @QueryParam("offset") @Min(0) @DefaultValue("0") offset: Int,
        @ApiParam("Sort the users by the given conditions") @QueryParam("sortByAttribute") @DefaultValue("ID") sortByAttribute: UserAttribute,
        @ApiParam("The sorting order of the resulting list") @QueryParam("sortOrderType") @DefaultValue("ASC") sortOrderType: Order,
        @ApiParam("Whether or not to return deleted users") @QueryParam("isShowDeletedUserMode") @DefaultValue("false") isShowDeletedUserMode: Boolean
    ): List<User> {
        return userService.findAllUsers(
            isNull = isShowDeletedUserMode,
            userAttribute = sortByAttribute,
            orderType = sortOrderType,
            numberOfUsers = limit,
            offsetUserNumber = offset
        )
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Modify user data")
    @ApiResponses(
        ApiResponse(code = HttpStatus.OK_200, message = "OK"),
        ApiResponse(code = HttpStatus.NOT_FOUND_404, message = USER_NOT_FOUND_INFO)
    )
    fun updateUser(
        @ApiParam("User ID to update", required = true)
        @PathParam("id") userId: Int,
        @Valid
        @ApiParam("User data to update", required = true)
        userUpdateDto: UserUpdate
    ): Response {
        val userById = userService.findUserById(userId)
        if (userById == null || userById.deletedAt != null)
            throw WebApplicationException(format(USER_NOT_FOUND, userId), Response.Status.NOT_FOUND)

        return Response.status(HttpStatus.OK_200)
            .entity(userService.updateUser(userId, userUpdateDto))
            .build()
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete user by specified id")
    @ApiResponses(
        ApiResponse(code = HttpStatus.NO_CONTENT_204, message = USER_DELETED),
        ApiResponse(code = HttpStatus.NOT_FOUND_404, message = USER_NOT_FOUND_INFO)
    )
    fun deleteUserById(
        @ApiParam("User ID to delete", required = true)
        @PathParam("id")
        userId: Int
    ) {
        val userById = userService.findUserById(userId)
        if (userById == null || userById.deletedAt != null)
            throw WebApplicationException(format(USER_NOT_FOUND, userId), Response.Status.NOT_FOUND)

        return userService.deleteUserById(userId)
    }
}
