package org.msa.basekmm.utils

import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase
import org.msa.basekmm.data.database.NewsDatabase


expect fun shareLink(url: String)

expect fun randomUUIDStr():String

expect fun getType():Type

@Composable
expect fun getScreenSize():Size

expect fun getDatabaseBuilder() : RoomDatabase.Builder<NewsDatabase>