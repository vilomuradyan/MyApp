package tech.ntic.di.moduls

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import tech.ntic.App
import tech.ntic.data.database.AppDatabase
import tech.ntic.data.repository.Repository
import tech.ntic.data.repository.RepositoryHelper
import tech.ntic.utils.AppConstants.Companion.DATABASE_NAME
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {


    @Provides
fun providesApp():App = app

    @Provides
    @Singleton
    internal fun providesContext(): Context = app.applicationContext

    @Provides
    internal fun providesCompositeDisposable() = CompositeDisposable()

    @Singleton
    @Provides
    internal fun providesAppDatabase(context: Context) : AppDatabase =
        Room.databaseBuilder(context , AppDatabase::class.java ,DATABASE_NAME).fallbackToDestructiveMigration().build()

    @Provides
    internal fun providesRepository(repository: Repository): RepositoryHelper = repository

}