package tech.ntic.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import tech.ntic.data.database.entity.Profile

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile) : Completable

    @Query("Delete from Profile")
    fun delete(): Completable

    @Query("Update Profile set status = :status Where id = :id")
    fun updateById(id: Long , status: Boolean): Completable

    @Query("SELECT * FROM Profile")
    fun result(): Single<Profile>
}