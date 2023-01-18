package com.crm.umt.config

import com.crm.umt.controller.UserController
import com.crm.umt.mapper.UserMapper
import com.crm.umt.repository.UserRepository
import com.crm.umt.service.UserService
import com.crm.umt.service.UserServiceImpl
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.setup.Environment
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.mapstruct.factory.Mappers

object UserMngtDiConfiguration {

    fun getRepositoryDiModule(databaseSource: DataSourceFactory, environment: Environment): DI.Module {
        return DI.Module("userMngtRepositoryModule") {
            bind<JdbiFactory>() with singleton { JdbiFactory() }
            bind {
                singleton {
                    JdbiFactory().build(
                        environment,
                        databaseSource,
                        "h2_db"
                    ).apply {
                        installPlugin(SqlObjectPlugin())
                        installPlugin(KotlinPlugin())
                        installPlugin(KotlinSqlObjectPlugin())}
                }
            }
        }
    }

    val userMngtApiModule = DI.Module("userMngtApi") {
        bind<UserMapper>() with singleton { Mappers.getMapper(UserMapper::class.java) }
        bind<UserRepository>() with singleton { instance<Jdbi>().onDemand(UserRepository::class.java) }
        bind<UserService>() with singleton { UserServiceImpl(instance(), instance()) }
    }

    val userMngtWeb = DI.Module("userMngtWeb") {
        bind { singleton { UserController(instance()) } }
    }

    fun getUserMngtDiContainer(databaseSource: DataSourceFactory, environment: Environment): DI {
        return DI {
            importAll(
                getRepositoryDiModule(databaseSource, environment),
                userMngtApiModule,
                userMngtWeb
            )
        }
    }
}