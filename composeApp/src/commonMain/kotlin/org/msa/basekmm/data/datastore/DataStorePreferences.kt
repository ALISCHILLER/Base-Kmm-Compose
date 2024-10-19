package org.msa.basekmm.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.* // وارد کردن کتب‌خانه Coroutine برای مدیریت کدهای غیرهمزمان
import kotlinx.coroutines.internal.SynchronizedObject // وارد کردن کلاس برای همگام‌سازی
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath // وارد کردن تابع تبدیل رشته به مسیر

// تابع انتظاری برای ایجاد DataStore، پیاده‌سازی آن در هر پلتفرم متفاوت خواهد بود
expect fun dataStorePreferences(): DataStore<Preferences>

// شیء برای تنظیمات اپلیکیشن
object AppSettings {
    private lateinit var dataStore: DataStore<Preferences> // یک DataStore برای ذخیره تنظیمات
    @OptIn(InternalCoroutinesApi::class) // علامت‌گذاری برای استفاده از APIهای داخلی Coroutine
    private val lock = SynchronizedObject() // ایجاد یک شیء برای همگام‌سازی

    @OptIn(InternalCoroutinesApi::class) // علامت‌گذاری برای استفاده از APIهای داخلی Coroutine
    fun getDataStore(producePath: () -> String): DataStore<Preferences> {
        return synchronized(lock) { // همگام‌سازی برای جلوگیری از شرایط مسابقه
            if (AppSettings::dataStore.isInitialized) { // بررسی اینکه آیا DataStore قبلاً مقداردهی شده است
                dataStore // اگر مقداردهی شده باشد، آن را برمی‌گرداند
            } else {
                // اگر مقداردهی نشده باشد، DataStore را ایجاد می‌کند
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = { producePath().toPath() } // تبدیل مسیر تولید شده به نوع Path
                ).also { dataStore = it } // DataStore جدید را ذخیره می‌کند
            }
        }
    }
}
