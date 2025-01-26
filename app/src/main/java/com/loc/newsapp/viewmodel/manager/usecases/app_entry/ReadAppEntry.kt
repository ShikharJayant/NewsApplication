package com.loc.newsapp.viewmodel.manager.usecases.app_entry

import com.loc.newsapp.viewmodel.manager.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {
     operator fun invoke(): Flow <Boolean> {
        return localUserManger.readAppEntry()
    }


}