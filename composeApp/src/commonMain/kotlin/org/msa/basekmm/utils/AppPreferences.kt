package org.msa.basekmm.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map



class AppPreferences(
    private val dataStore: DataStore<Preferences>
) {
    private val themeKey = stringPreferencesKey("theme")

    /**
     * دریافت تم فعلی از DataStore
     * @return نام تم فعلی (به عنوان رشته)
     */
    suspend fun getTheme(): String = dataStore.data.map { preferences ->
        preferences[themeKey] ?: Theme.DARK_MODE.name // برگرداندن تم پیش‌فرض
    }.first()

    /**
     * تغییر تم اپلیکیشن
     * @param value نام تم جدید
     */
    suspend fun changeThemeMode(value: String) {
        dataStore.edit { preferences ->
            preferences[themeKey] = value // ذخیره تم جدید
        }
    }
}
