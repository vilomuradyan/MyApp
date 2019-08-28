package com.example.ntictask.di.component

import androidx.annotation.Keep
import tech.ntic.di.moduls.AppModule
import dagger.Component
import tech.ntic.viewModel.*
import javax.inject.Singleton

@Keep
@Singleton
@Component(modules = [AppModule::class])
interface DIComponent {

    interface Injectable{
        fun inject(diComponent: DIComponent)
    }

    fun inject(splashViewModel: SplashViewModel)
    fun inject(mainViewModel: MainViewModel)
    fun inject(loginViewModel: LoginViewModel)
    fun inject(signUpViewModel: SignUpViewModel)
    fun inject(passwordResetViewModel: PasswordResetViewModel)
    fun inject(connectedViewModel: ConnectedViewModel)
}