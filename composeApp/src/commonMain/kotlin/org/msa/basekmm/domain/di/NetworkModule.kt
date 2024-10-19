package org.msa.basekmm.domain.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.msa.basekmm.utils.BASE_URL
import org.msa.basekmm.utils.*
import co.touchlab.kermit.Logger as KermitLogger // استفاده از kermit برای logging

@OptIn(ExperimentalSerializationApi::class) // استفاده از قابلیت‌های experimental در kotlinx.serialization
val networkModule = module {
    // این کد یک singleton از HttpClient را در Koin ثبت می‌کند.
    single {
        HttpClient {
            // درخواست‌های پیش‌فرض تنظیم می‌شود.
            defaultRequest {
                url(BASE_URL) // از متغیر محیطی BASE_URL برای تنظیم URL پایه استفاده می‌شود.
                contentType(ContentType.Application.Json) // تمام درخواست‌ها به صورت JSON ارسال می‌شوند.
            }

            // نصب پلاگین HttpTimeout برای مدیریت زمان‌های انتظار درخواست و پاسخ.
            install(HttpTimeout) {
                socketTimeoutMillis = SOCKET_TIMEOUT.toLong() // زمان انتظار برای برقراری اتصال به سرور
                requestTimeoutMillis = REQUEST_TIMEOUT.toLong() // زمان انتظار برای تکمیل درخواست
            }

            // نصب پلاگین ContentNegotiation برای مدیریت serialization/deserialization
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = DEBUG // اگر حالت دیباگ باشد، خروجی به صورت فرمت زیبا نمایش داده می‌شود.
                    isLenient = true // انعطاف در پردازش داده‌های JSON (به عنوان مثال قبول JSONهای ناقص)
                    ignoreUnknownKeys = true // در صورت وجود کلیدهای ناشناخته در JSON، آنها را نادیده می‌گیرد.
                    explicitNulls = false // مقادیر null در JSON نادیده گرفته می‌شوند و ارسال نمی‌گردند.
                })
            }

            // نصب پلاگین Logging برای مدیریت لاگ‌های مربوط به درخواست‌ها و پاسخ‌ها
            install(Logging) {
                // سطح لاگ‌گیری بسته به حالت برنامه تغییر می‌کند
                level = if (DEBUG) LogLevel.ALL else LogLevel.INFO

                // ثبت لاگ با استفاده از Kermit logger
                logger = object : Logger {
                    override fun log(message: String) {
                        KermitLogger.d(tag = "KtorClient", null) {
                            message // هر پیام لاگ به عنوان debug در Kermit ثبت می‌شود.
                        }
                    }
                }
            }
        }
    }
}
