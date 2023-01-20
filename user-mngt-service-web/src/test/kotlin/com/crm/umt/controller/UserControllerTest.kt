package com.crm.umt.controller

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response
import java.lang.String
import java.time.Instant
import java.util.*

import com.crm.umt.controller.utils.InstantDeserializer
import com.crm.umt.controller.utils.InstantSerializer
import com.crm.umt.controller.utils.buildUser
import com.crm.umt.controller.validation.ErrorMessage
import com.crm.umt.dto.user.User
import com.crm.umt.mapper.UserMapper
import com.crm.umt.repository.UserRepository
import com.crm.umt.service.UserServiceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule

import io.dropwizard.jackson.Jackson
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport
import io.dropwizard.testing.junit5.ResourceExtension
import io.mockk.every
import io.mockk.mockk

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*


@ExtendWith(DropwizardExtensionsSupport::class)
internal class UserControllerTest {

    private val userRepository = mockk<UserRepository>()
    private val userMapper = mockk<UserMapper>()
    private val userService = UserServiceImpl(userRepository, userMapper)

    private val resourceExtension: ResourceExtension = ResourceExtension.builder()
        .addResource(UserController(userService))
        .build()

    companion object {
        const val DEFAULT_USER_ID: Int = 1
        const val NOT_FOUND: Int = 404
        const val CONFLICT: Int = 409

        private lateinit var jacksonMapper: ObjectMapper

        @JvmStatic
        @BeforeAll
        fun beforeClass() {
            jacksonMapper = Jackson.newObjectMapper()
            jacksonMapper.apply {
                registerModule(
                    SimpleModule("SerializerDeserializerModule").also {
                        it.addSerializer(Instant::class.java, InstantSerializer())
                        it.addDeserializer(Instant::class.java, InstantDeserializer())
                    }
                )
            }
        }
    }

    @Test
    fun findUserByIdReturnsOkStatus() {
        val testUser = buildUser(false)
        val testUserId: Int = testUser.id

        every { userService.findUserById(testUserId) } returns testUser
        println(testUser)

        val userRetrievedRequest = resourceExtension.target("/users/$testUserId").request()
        val userRetrieved = userRetrievedRequest.get(User::class.java)

        io.mockk.verify { userService.findUserById(testUserId) }
        assertThat(userRetrieved).isEqualTo(testUser)
        assertThat(
            userRetrievedRequest.get().getStatusInfo().getStatusCode()
        ).isEqualTo(Response.Status.OK.getStatusCode())
    }

    @Test
    fun findUserByIdReturnsNotFoundStatus() {
        val testUserId: Int = 2
        every { userService.findUserById(testUserId) } returns null

        val responseNotFound = resourceExtension.target("/users/$testUserId").request().get();

        io.mockk.verify { userService.findUserById(testUserId) }
        assertThat(responseNotFound.getStatusInfo().getStatusCode()).isEqualTo(NOT_FOUND)
    }

    @Test
    fun deleteUserByIdReturnsOkStatus() {
        val notDeletedUser = buildUser(false)
        val instantNow = Instant.now()
        val deletedUser = notDeletedUser.copy(deletedAt = instantNow)

        val userId = notDeletedUser.id

        // Given
        every { userService.findUserById(userId) } returns notDeletedUser
        every { userService.deleteUserById(userId) } returns deletedUser

        val deletedUserOk = resourceExtension.target("/users/$userId").request().delete(User::class.java)

        // Then
        io.mockk.verify { userService.findUserById(userId) }
        io.mockk.verify { userService.deleteUserById(userId) }

        assertThat(instantNow).isEqualTo(deletedUserOk.deletedAt)
        assertThat(deletedUser.deletedAt).isEqualTo(deletedUserOk.deletedAt)
    }

    @Test
    fun deleteUserByIdReturnsNotFoundStatus() {
        // Given
        every { userService.findUserById(DEFAULT_USER_ID) } returns null
        every { userService.deleteUserById(DEFAULT_USER_ID) }.throws(
            WebApplicationException(
                String.format(
                    ErrorMessage.USER_NOT_FOUND,
                    DEFAULT_USER_ID
                ), Response.Status.NOT_FOUND
            )
        )

        // When
        val responseNotFound = resourceExtension.target("/users/$DEFAULT_USER_ID").request().delete()

        // Then
        assertThat(responseNotFound.status).isEqualTo(NOT_FOUND)
    }

    @Test
    fun deleteDeletedUserByIdReturnsNotFoundStatus() {
        val deletedUser = buildUser(true)
        val deletedUserId = deletedUser.id

        // Given
        every { userService.findUserById(deletedUserId) } returns deletedUser

        // When
        val responseNotFound = resourceExtension.target("/users/$deletedUserId").request().delete()

        // Then
        assertThat(responseNotFound.status).isEqualTo(NOT_FOUND)
    }

    @Test
    fun createUserReturnsUserAlreadyExistsStatus() {
        // TODO: modify json mapping of user DTO - ex.: "firstName" -> "first_name"
//        val userToCreate = buildUserCreate()
//        val existingUser = buildUserEntity(false, userToCreate.email)
//
//        // Given
//        every { userService.findUserByEmail(userToCreate.email) } returns existingUser
//        every { userService.createUser(userToCreate) }.throws(WebApplicationException(
//            String.format(
//                ErrorMessage.USER_ALREADY_EXISTS,
//                userToCreate.email
//            ), Response.Status.CONFLICT))
//
//        // When
//        val userAlreadyExists = resourceExtension.target("/users").request().post(Entity.json(jacksonMapper.writeValueAsString(userToCreate)))
//
//        // Then
//        assertThat(userAlreadyExists.status).isEqualTo(CONFLICT)
    }
}