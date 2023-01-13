package com.crm.umt.config

import com.fasterxml.jackson.annotation.JsonProperty

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory

// TBD: remove extra members
class DropwizardUserMngtConfiguration(
    val database: DataSourceFactory = DataSourceFactory(),
    private val test: String = "test"
) : Configuration() {
    companion object {
        const val DB: String = "database"
    }

//    @JsonProperty(DB)
//    fun getDataSourceFactory(): DataSourceFactory? {
//        return database
//    }

    @JsonProperty("test")
    fun getTest(): String {
        return test
    }
}