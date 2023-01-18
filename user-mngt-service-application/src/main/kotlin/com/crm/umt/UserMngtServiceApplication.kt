package com.crm.umt

import java.time.Instant

import com.codahale.metrics.MetricRegistry
import com.crm.umt.utils.createFlywayBundle
import com.crm.umt.utils.createSwaggerBundle
import com.crm.umt.config.DropwizardUserMngtConfiguration
import com.crm.umt.config.UserMngtDiConfiguration
import com.crm.umt.controller.UserController
import com.crm.umt.utils.InstantDeserializer
import com.crm.umt.utils.InstantSerializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

import io.dropwizard.Application
import io.dropwizard.flyway.FlywayBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

import org.kodein.di.instance
import org.kodein.di.newInstance

class UserMngtServiceApplication : Application<DropwizardUserMngtConfiguration>() {
    private val flywayBundle: FlywayBundle<DropwizardUserMngtConfiguration> = createFlywayBundle()

    override fun run(configuration: DropwizardUserMngtConfiguration, environment: Environment) {
        environment.objectMapper.apply {
            registerModule(
                SimpleModule("SerializerDeserializerModule").also {
                    it.addSerializer(Instant::class.java, InstantSerializer())
                    it.addDeserializer(Instant::class.java, InstantDeserializer())
                }
            )
            registerKotlinModule()
        }

        flywayBundle.getFlywayFactory(configuration)
            .build(
                configuration.getDatabase()
                .build(MetricRegistry(), "Flyway")
            ).migrate()

        val userResource: UserController by UserMngtDiConfiguration.getUserMngtDiContainer(
            configuration.getDatabase(),
            environment
        ).newInstance { UserController(instance()) }
        environment.jersey().register(userResource)
    }

    override fun initialize(bootstrap: Bootstrap<DropwizardUserMngtConfiguration>) {
        bootstrap.addBundle(flywayBundle)
        bootstrap.addBundle(createSwaggerBundle())
    }
}

fun main(args: Array<String>) {
    UserMngtServiceApplication().run(*args)
}