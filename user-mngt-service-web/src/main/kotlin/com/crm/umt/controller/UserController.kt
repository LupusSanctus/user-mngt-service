package com.crm.umt.controller

import com.crm.umt.service.UserService
import com.crm.umt.dto.UserDto

import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.POST
import javax.ws.rs.DELETE

import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.Consumes
import javax.ws.rs.PathParam

import javax.ws.rs.core.MediaType

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
class UserController(private val userService: UserService) {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createUser(userDto: UserDto) {
        userService.createUser(userDto)
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findUserById(userId: Int): UserDto {
        return userService.findUserById(userId)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAllUsers(): List<UserDto> {
        return userService.findAllUsers()
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateUser(userDto: UserDto) {
        return userService.updateUser(userDto)
    }
    @DELETE
    @Path("/{id}")
    fun deleteUserById(@PathParam("id") userId: Int) {
        return userService.deleteUserById(userId)
    }
}
