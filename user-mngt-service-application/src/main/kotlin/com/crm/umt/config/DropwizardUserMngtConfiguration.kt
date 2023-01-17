package com.crm.umt.config

import com.fasterxml.jackson.annotation.JsonProperty

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.flyway.FlywayFactory
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration

class DropwizardUserMngtConfiguration(
    private val database: DataSourceFactory = DataSourceFactory(),
    private val flyway: FlywayFactory = FlywayFactory(),
    private val swagger: SwaggerBundleConfiguration = SwaggerBundleConfiguration()
) : Configuration() {
    companion object {
        const val DB: String = "database"
        const val FLYWAY: String = "flyway"
        const val SWAGGER: String = "swagger"
    }

    @JsonProperty(DB)
    fun getDatabase(): DataSourceFactory {
        return database
    }

    @JsonProperty(FLYWAY)
    fun getFlyway(): FlywayFactory {
        return flyway
    }

    @JsonProperty(SWAGGER)
    fun getSwagger(): SwaggerBundleConfiguration {
        return swagger
    }
}