package com.terrago.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.terrago.app.database.DatabaseDriverFactory
import com.terrago.app.db.TerraGoDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val database = TerraGoDatabase(DatabaseDriverFactory(LocalContext.current).createDriver())
            App(database)
        }
    }
}