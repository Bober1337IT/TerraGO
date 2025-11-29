package com.terrago.app.database

import android.content.Context
import com.terrago.app.db.TerraGoDatabase

// Singleton of database (ensures SQLDelight is initialized only once)
object DatabaseProvider{

    private var instance: TerraGoDatabase? = null

    // Returns the single database instance (creates it if needed)
    fun getDatabase(context: Context): TerraGoDatabase{
        return instance ?: TerraGoDatabase(
            DatabaseDriverFactory(context).createDriver()
        ).also { instance = it }
    }
}