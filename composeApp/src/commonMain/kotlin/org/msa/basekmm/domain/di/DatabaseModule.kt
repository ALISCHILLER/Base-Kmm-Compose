package org.msa.basekmm.domain.di // تعیین پکیج ماژول DI (Dependency Injection)

import androidx.room.RoomDatabase // وارد کردن کلاس RoomDatabase برای مدیریت پایگاه داده
import androidx.sqlite.driver.bundled.BundledSQLiteDriver // وارد کردن درایور SQLite برای کار با پایگاه داده
import org.msa.basekmm.data.datastore.dataStorePreferences // وارد کردن تابع برای تولید DataStore از نوع Preferences
import kotlinx.coroutines.Dispatchers // وارد کردن Dispatchers برای تعیین زمینه اجرای Coroutine
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext // وارد کردن withContext برای تغییر زمینه Coroutine
import org.koin.dsl.module // وارد کردن Koin DSL برای تعریف ماژول‌ها
import org.msa.basekmm.data.database.NewsDatabase // وارد کردن کلاس NewsDatabase که مدل پایگاه داده است
import org.msa.basekmm.utils.AppPreferences // وارد کردن کلاس AppPreferences برای مدیریت تنظیمات اپلیکیشن
import org.msa.basekmm.utils.getDatabaseBuilder // وارد کردن تابع getDatabaseBuilder برای دریافت سازنده پایگاه داده

// تعریف ماژول پایگاه داده
val databaseModule = module {
    // تعریف یک singleton برای پایگاه داده NewsDatabase
    single {
        getRoomDatabase(getDatabaseBuilder()) // ایجاد پایگاه داده با استفاده از تابع getRoomDatabase
    }

    // تعریف یک singleton برای AppPreferences با استفاده از dataStorePreferences
    single {
        AppPreferences(dataStorePreferences()) // ایجاد یک نمونه از AppPreferences
    }
}

// تابع برای ایجاد پایگاه داده با استفاده از سازنده
fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>, // ورودی: سازنده پایگاه داده
): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver()) // تعیین درایور SQLite برای پایگاه داده
        .setQueryCoroutineContext(Dispatchers.IO) // تعیین زمینه اجرای Coroutine برای درخواست‌های پایگاه داده
        .build() // ساخت و برگرداندن نمونه پایگاه داده
}


suspend fun clearDatabase(database: NewsDatabase) {
    withContext(Dispatchers.IO) { // تغییر زمینه به IO برای عملیات ورودی/خروجی
        database.clearAllTables() // پاک کردن تمام جداول پایگاه داده
    }
}

// تابع برای بررسی صحت اتصال به پایگاه داده
suspend fun isDatabaseInitialized(database: NewsDatabase): Boolean {
    return withContext(Dispatchers.IO) {
        // فرض کنید متدی به نام isOpen وجود دارد که وضعیت پایگاه داده را بررسی می‌کند
        database.isDatabaseOpen() // چک کردن وضعیت پایگاه داده
    }
}
