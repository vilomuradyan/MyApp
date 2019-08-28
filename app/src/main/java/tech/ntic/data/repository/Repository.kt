package tech.ntic.data.repository

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Single
import tech.ntic.data.database.AppDatabase
import tech.ntic.data.database.entity.Profile
import tech.ntic.utils.iomain
import javax.inject.Inject

class Repository @Inject constructor(private val context: Context, private val dataBase: AppDatabase) : RepositoryHelper {

    override fun insert(profile: Profile): Completable = dataBase.profile.insert(profile).iomain()

    override fun profile(): Single<Profile> = dataBase.profile.result()
}