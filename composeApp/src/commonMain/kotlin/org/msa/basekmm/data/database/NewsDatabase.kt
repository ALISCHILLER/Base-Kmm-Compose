package org.msa.basekmm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.msa.basekmm.data.database.dao.NewsDao
import org.msa.basekmm.data.database.typeConverter.SourceTypeConverter
import org.msa.basekmm.data.model.Article

// دیتابیس Room با جدول Article و نسخه 1
@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(), DB {


    // Flag برای نشان دادن وضعیت باز بودن پایگاه داده
    private var isOpen: Boolean = false

    // دسترسی به DAO مربوط به عملیات روی جدول Article
    abstract fun newsDao(): NewsDao

    // پاک کردن تمام جداول دیتابیس
    override fun clearAllTables() {
        // اجرای عملیات پایه برای پاک کردن تمامی جداول
        super.clearAllTables()
    }


    // متد برای باز کردن پایگاه داده و تنظیم Flag
    fun openDatabase() {
        // باز کردن پایگاه داده (یا انجام کارهای لازم برای باز شدن)
        isOpen = true
    }

    // متد برای بستن پایگاه داده و تنظیم Flag
    fun closeDatabase() {
        // بستن پایگاه داده (یا انجام کارهای لازم برای بسته شدن)
        isOpen = false
    }

    // متد بررسی باز بودن پایگاه داده
    fun isDatabaseOpen(): Boolean {
        return isOpen
    }
}

// اینترفیس DB برای مدیریت عمومی دیتابیس
interface DB {
    // متدی برای پاک کردن تمامی جداول
    fun clearAllTables(): Unit {
        // می‌توانید در اینجا کدی برای پاک کردن جداول خاص اضافه کنید
    }
}
