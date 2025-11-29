package com.terrago.app.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.terrago.app.db.TerraGoDatabase

class DatabaseDriverFactory(
    private val context: Context
) {
    fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = TerraGoDatabase.Schema,
            context = context,
            name = "TerraGoDatabase.db"
        )
    }
}
// Put this into terminal and run it to create db in kotlin
// ./gradlew generateDebugTerraGoDatabaseInterface
// ./gradlew generateReleaseTerraGoDatabaseInterface