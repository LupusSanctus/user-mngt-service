package com.crm.umt

import com.crm.umt.config.DropwizardUserMngtConfiguration
import com.crm.umt.config.UserMngtDiConfiguration
import com.crm.umt.controller.UserController
import io.dropwizard.Application
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.flyway.FlywayBundle
import io.dropwizard.flyway.FlywayFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.federecio.dropwizard.swagger.SwaggerBundle
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration
import org.flywaydb.core.Flyway
import org.kodein.di.instance
import org.kodein.di.newInstance


class UserMngtServiceApplication : Application<DropwizardUserMngtConfiguration>() {

    override fun run(configuration: DropwizardUserMngtConfiguration, environment: Environment) {
//        val flyway: Flyway = Flyway()
//        flyway.migrate()

        val userResource: UserController by UserMngtDiConfiguration.getUserMngtDiContainer(
            configuration.getDatabase(),
            environment
        ).newInstance { UserController(instance()) }
        environment.jersey().register(userResource)
    }

    override fun initialize(bootstrap: Bootstrap<DropwizardUserMngtConfiguration>) {
        // Flyway
        bootstrap.addBundle(object : FlywayBundle<DropwizardUserMngtConfiguration>() {
            override fun getDataSourceFactory(configuration: DropwizardUserMngtConfiguration): DataSourceFactory {
                return configuration.getDatabase()
            }

            override fun getFlywayFactory(configuration: DropwizardUserMngtConfiguration): FlywayFactory {
                return configuration.getFlyway()
            }
        })

        // Swagger
        bootstrap.addBundle(object : SwaggerBundle<DropwizardUserMngtConfiguration>() {
            override fun getSwaggerBundleConfiguration(configuration: DropwizardUserMngtConfiguration): SwaggerBundleConfiguration {
                return configuration.getSwagger()
            }
        })
    }
}

fun main(args: Array<String>) {
    UserMngtServiceApplication().run(*args)
}