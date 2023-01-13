package com.crm.umt

import com.crm.umt.config.DropwizardUserMngtConfiguration
import com.crm.umt.config.UserMngtDIConfiguration
import com.crm.umt.controller.UserController

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

import org.kodein.di.instance
import org.kodein.di.newInstance


class UserMngtServiceApplication : Application<DropwizardUserMngtConfiguration>() {
    // TBD: reimplement/ clean code | add healthcheck!
    override fun run(configuration: DropwizardUserMngtConfiguration, environment: Environment) {
        val userResource: UserController by UserMngtDIConfiguration.userMngtDIContainer.newInstance {
            UserController(instance())
        }
        environment.jersey().register(userResource)
    }

    override fun initialize(bootstrap: Bootstrap<DropwizardUserMngtConfiguration>) {
        // TBD
    }
}

fun main(args: Array<String>) {
    UserMngtServiceApplication().run(*args)
}