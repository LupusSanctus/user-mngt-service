package com.crm.umt.config

import com.fasterxml.jackson.annotation.JsonProperty

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration

import javax.validation.Valid
import javax.validation.constraints.NotNull

// TBD: remove extra members
class DropwizardUserMngtConfiguration(
     val database: DataSourceFactory = DataSourceFactory(),
     val test: String = "test"
) : Configuration() {
//    companion object {
//        const val DB: String = "database"
//    }

//    @JsonProperty(DB)
//    fun getDataSourceFactory(): DataSourceFactory? {
//        return database
//    }
//
//    @JsonProperty("test")
//    fun getTest(): String {
//        return test
//    }
}