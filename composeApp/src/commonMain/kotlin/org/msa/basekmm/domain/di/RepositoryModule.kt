package org.msa.basekmm.domain.di // بسته‌ای که حاوی ماژول‌های وابستگی Koin است

import org.koin.dsl.module // وارد کردن کتابخانه Koin برای تعریف ماژول‌های وابستگی
import org.msa.basekmm.data.database.NewsDatabase // وارد کردن کلاس دیتابیس برای دسترسی به DAOها
import org.msa.basekmm.data.repository.LocalNewsRepository // وارد کردن مخزن محلی برای عملیات مربوط به دیتابیس
import org.msa.basekmm.data.repository.OnlineNewsRepository // وارد کردن مخزن آنلاین برای عملیات مربوط به API

// ماژول Koin برای مدیریت وابستگی‌های مخزن
val repositoryModule = module {
    // تعریف یک singleton از LocalNewsRepository
    single {
        // دریافت DAO از دیتابیس و ایجاد یک نمونه از LocalNewsRepository
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }

    // تعریف یک singleton از OnlineNewsRepository
    single {
        // ایجاد یک نمونه از OnlineNewsRepository با استفاده از HttpClient
        OnlineNewsRepository(get())
    }
}
