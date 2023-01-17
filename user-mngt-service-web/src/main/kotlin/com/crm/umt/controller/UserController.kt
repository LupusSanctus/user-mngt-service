package com.crm.umt.controller

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
import javax.ws.rs.core.MediaType

import com.crm.umt.constants.UserAttribute
import com.crm.umt.dto.user.UserDto
import com.crm.umt.dto.user.UserReducedDto
import com.crm.umt.repository.db.utils.Order
import com.crm.umt.repository.db.utils.SqlConstants.NOT_NULL
import com.crm.umt.repository.db.utils.SqlConstants.NULL
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
    @ApiOperation(value = "Create a new setting the specified values")
    @ApiResponses(ApiResponse(code = HttpStatus.CREATED_201, message = "OK"))
    fun createUser(@ApiParam("User data", required = true) userDto: UserReducedDto): UserDto {
        return userService.createUser(userDto)
    }

    @GET
    @Path("/{id}")
    @ApiResponses(ApiResponse(code = HttpStatus.OK_200, message = "OK"))
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Find the user by specified user id")
    fun findUserById(@ApiParam("User ID", required = true) userId: Int): UserDto {
        return userService.findUserById(userId)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve the users modifying the list with set parameters")
    @ApiResponses(ApiResponse(code = HttpStatus.OK_200, message = "OK"))
    fun findAllUsers(
        @QueryParam("limit") @Min(1) @Max(100) @DefaultValue("25") limit: Int,
        @QueryParam("offset") @Min(0) @DefaultValue("0") offset: Int,
        @QueryParam("sortByAttribute") @DefaultValue("id") sortByAttribute: UserAttribute,
        @QueryParam("sortOrderType") @DefaultValue("ASC") sortOrderType: Order,
        @QueryParam("isShowDeletedMode") @DefaultValue("false") isShowDeletedMode: Boolean
    ): List<UserDto> {
        return userService.findAllUsers(
            isNull = if (isShowDeletedMode) NULL else NOT_NULL,
            userAttribute = sortByAttribute,
            orderType = sortOrderType,
            numberOfUsers = limit,
            offsetUserNumber = offset
        )
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Modify user data")
    @ApiResponses(ApiResponse(code = HttpStatus.OK_200, message = "OK"))
    fun updateUser(@ApiParam("User data to update", required = true) userDto: UserReducedDto): UserDto? {
        return userService.updateUser(userDto)
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete user by specified id")
    @ApiResponses(ApiResponse(code = HttpStatus.OK_200, message = "OK"))
    fun deleteUserById(@ApiParam("User ID", required = true) @PathParam("id") userId: Int) {
        return userService.deleteUserById(userId)
    }
}
