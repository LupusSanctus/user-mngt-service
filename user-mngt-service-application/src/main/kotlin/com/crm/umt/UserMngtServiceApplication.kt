package com.crm.umt

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

import com.crm.umt.config.DropwizardUserMngtConfiguration
import com.crm.umt.service.UserServiceImpl
import com.crm.umt.controller.UserController


class UserMngtServiceApplication : Application<DropwizardUserMngtConfiguration>() {
    // TBD: reimplement/ clean code | add healthcheck!
    override fun run(configuration: DropwizardUserMngtConfiguration, environment: Environment) {
        val resource = UserController(userServiceImpl())
        println(configuration.test)
        environment.jersey().register(resource)
    }

    override fun initialize(bootstrap: Bootstrap<DropwizardUserMngtConfiguration>) {
        // TBD
    }
}

fun main(args: Array<String>) {
    UserMngtServiceApplication().run(*args)
}