package org.msa.basekmm.data.repository

import io.ktor.client.HttpClient // وارد کردن کلاس HttpClient از Ktor برای ارسال درخواست‌های HTTP
import io.ktor.client.call.HttpClientCall // وارد کردن کلاس HttpClientCall برای مدیریت درخواست‌ها
import io.ktor.client.request.get // وارد کردن تابع get برای ارسال درخواست GET
import io.ktor.client.request.parameter // وارد کردن تابع parameter برای افزودن پارامتر به درخواست
import io.ktor.client.request.url // وارد کردن تابع url برای تعیین URL درخواست
import io.ktor.client.statement.HttpResponse // وارد کردن کلاس HttpResponse برای مدیریت پاسخ‌ها
import io.ktor.utils.io.errors.IOException // وارد کردن IOException برای مدیریت خطاهای ورودی/خروجی
import kotlinx.coroutines.Dispatchers // وارد کردن Dispatchers برای مدیریت کنسول‌های همزمان
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext // وارد کردن withContext برای تغییر زمینه همزمان

class OnlineNewsRepository(
    private val httpClient: HttpClient // وابستگی HttpClient برای برقراری ارتباط شبکه
) {
    // تابعی برای دریافت اخبار با توجه به دسته‌بندی
    suspend fun getNews(category: String): HttpResponse {
        return withContext(Dispatchers.IO) { // تغییر زمینه به IO برای انجام عملیات ورودی/خروجی
            try {
                // ارسال درخواست GET به API و دریافت اخبار
                httpClient.get {
                    url("everything") // URL مربوط به دریافت اخبار
                    parameter("q", category) // افزودن پارامتر دسته‌بندی به درخواست
                    parameter("apiKey", API_KEY) // افزودن کلید API به درخواست
                }
            } catch (e: IOException) {
                // در صورتی که خطا در برقراری ارتباط وجود داشته باشد، خطا را مدیریت می‌کند
                throw Exception("خطا در دریافت اخبار: ${e.message}") // پرتاب یک استثنا با پیام خطا
            }
        }
    }

    // تابعی برای جستجوی اخبار با توجه به کوئری جستجو
    suspend fun searchNews(searchQuery: String): HttpResponse {
        return withContext(Dispatchers.IO) { // تغییر زمینه به IO برای انجام عملیات ورودی/خروجی
            try {
                // ارسال درخواست GET به API برای جستجوی اخبار
                httpClient.get {
                    url("everything") // URL مربوط به جستجوی اخبار
                    parameter("q", searchQuery) // افزودن پارامتر جستجو به درخواست
                    parameter("apiKey", API_KEY) // افزودن کلید API به درخواست
                }
            } catch (e: IOException) {
                // در صورتی که خطا در برقراری ارتباط وجود داشته باشد، خطا را مدیریت می‌کند
                throw Exception("خطا در جستجوی اخبار: ${e.message}") // پرتاب یک استثنا با پیام خطا
            }
        }
    }
}
