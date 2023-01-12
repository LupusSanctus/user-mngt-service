package com.crm.umt.controller

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import com.crm.umt.service.UserService
import com.crm.umt.dto.UserDto

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
class UserController(private val userService: UserService) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAllUsers(): List<UserDto> {
        return userService.findAllUsers()
    }
}
