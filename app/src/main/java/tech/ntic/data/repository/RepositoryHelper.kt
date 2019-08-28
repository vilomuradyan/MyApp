package tech.ntic.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import tech.ntic.data.database.entity.Profile

interface RepositoryHelper {

    fun insert(profile: Profile):Completable
    fun profile():Single<Profile>
}