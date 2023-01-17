package com.crm.umt.bundle

import com.crm.umt.config.DropwizardUserMngtConfiguration
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.flyway.FlywayBundle
import io.dropwizard.flyway.FlywayFactory
import io.federecio.dropwizard.swagger.SwaggerBundle
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration


fun createFlywayBundle() = object : FlywayBundle<DropwizardUserMngtConfiguration>() {
    override fun getDataSourceFactory(configuration: DropwizardUserMngtConfiguration): DataSourceFactory {
        return configuration.getDatabase()
    }

    override fun getFlywayFactory(configuration: DropwizardUserMngtConfiguration): FlywayFactory {
        return configuration.getFlyway()
    }
}

fun createSwaggerBundle() = object : SwaggerBundle<DropwizardUserMngtConfiguration>() {
    override fun getSwaggerBundleConfiguration(configuration: DropwizardUserMngtConfiguration): SwaggerBundleConfiguration {
        return configuration.getSwagger()
    }
}
