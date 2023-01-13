package com.crm.umt.config

import com.crm.umt.controller.UserController
import com.crm.umt.service.UserService
import com.crm.umt.service.UserServiceImpl

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
object UserMngtDIConfiguration {

    val userMngtApiModule = DI.Module("userMngtApi") {
        bind<UserService>() with instance(UserServiceImpl())
        //bind<UserMapper>() with instance(???)  TBD
    }

    val userMngtRepositoryModule = DI.Module("userMngtRepository") {
        // TBD: DB, repo
    }

    val userMngtWeb = DI.Module("userMngtWeb") {
        bind { singleton { UserController(instance()) } }
    }

    val userMngtDIContainer = DI {
        importAll(
            userMngtApiModule,
            userMngtRepositoryModule,
            userMngtWeb
        )
    }
}