package com.loc.newsapp.viewmodel.manager.usecases.app_entry

import com.loc.newsapp.viewmodel.manager.LocalUserManger

class SaveAppEntry (
    private val localUserManger: LocalUserManger)

{
    suspend operator fun invoke(){
        return localUserManger.saveAppEntry()
    }
}