package org.msa.basekmm.utils

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.mp.KoinPlatform
import org.msa.basekmm.data.database.NewsDatabase

actual fun shareLink(url: String) {
}

actual fun randomUUIDStr(): String {
    TODO("Not yet implemented")
}

actual fun getType(): Type {
    TODO("Not yet implemented")
}

@Composable
actual fun getScreenSize(): Size {
    TODO("Not yet implemented")
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    // 1. دریافت یک نمونه از Application از Koin
    val appContext = KoinPlatform.getKoin().get<Application>()

    // 2. ساخت مسیر فایل دیتابیس با استفاده از نام دیتابیس
    val dbFile = appContext.getDatabasePath(DB_Name)

    // 3. ایجاد سازنده دیتابیس Room برای NewsDatabase
    return Room.databaseBuilder<NewsDatabase>(
        context = appContext, // 4. تعیین Context برای دیتابیس
        name = dbFile.absolutePath // 5. تعیین نام دیتابیس بر اساس مسیر فایل
    )
}
