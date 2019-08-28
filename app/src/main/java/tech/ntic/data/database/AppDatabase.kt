package tech.ntic.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import tech.ntic.data.dao.ProfileDao
import tech.ntic.data.database.entity.Profile

@Database(entities = [Profile::class], version = 1 , exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract val profile: ProfileDao
}