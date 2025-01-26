package com.loc.newsapp.model.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.viewmodel.manager.LocalUserManger
import com.loc.newsapp.viewmodel.Constants
import com.loc.newsapp.viewmodel.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context


) : LocalUserManger {
     override suspend fun saveAppEntry(){
         context.dataStore.edit{
             settings -> settings[PreferencesKeys.APP_ENTRY] = true
         }

     }

     override  fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map{
            preferences ->
            preferences[PreferencesKeys.APP_ENTRY]?:false
        }
     }
}


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name  = USER_SETTINGS)

private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}