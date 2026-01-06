package com.terrago.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.terrago.app.database.DatabaseDriverFactory
import com.terrago.app.db.TerraGoDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val database = TerraGoDatabase(DatabaseDriverFactory(LocalContext.current).createDriver())
            App(database)
        }
    }
}