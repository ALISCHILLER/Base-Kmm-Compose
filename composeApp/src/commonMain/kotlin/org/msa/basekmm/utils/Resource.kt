package org.msa.basekmm.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

// تعریف کلاس Resource که نمایان‌گر وضعیت بارگذاری اطلاعات است
sealed class Resource<out T> {
    // وضعیت Idle به معنای عدم بارگذاری
    data object Idle : Resource<Nothing>()

    // وضعیت Loading به معنای در حال بارگذاری
    data object Loading : Resource<Nothing>()

    // وضعیت Success به معنای بارگذاری موفق با داده
    data class Success<T>(val data: T) : Resource<T>()

    // وضعیت Error به معنای بروز خطا با پیام
    data class Error(val message: String) : Resource<Nothing>()

    // تابعی برای نمایش نتیجه بر اساس وضعیت Resource
    @Composable
    fun DisplayResult(
        onIdle: (@Composable () -> Unit)? = null, // تابع برای وضعیت Idle
        onLoading: @Composable () -> Unit, // تابع برای وضعیت Loading
        onSuccess: @Composable (T) -> Unit, // تابع برای وضعیت Success
        onError: @Composable (String) -> Unit, // تابع برای وضعیت Error
    ) {
        // استفاده از AnimatedContent برای انیمیشن بین حالت‌ها
        AnimatedContent(
            targetState = this, // وضعیت فعلی
            transitionSpec = {
                FadeIn togetherWith FadeOut // ترکیب انیمیشن‌ها برای تغییر حالت
            },
            label = "Content Animation" // برچسب برای انیمیشن
        ) { state -> // تغییرات بر اساس وضعیت
            when (state) {
                is Idle -> {
                    onIdle?.invoke() // در صورت Idle، تابع مربوطه را فراخوانی می‌کند
                }

                is Loading -> {
                    onLoading() // در صورت Loading، تابع مربوطه را فراخوانی می‌کند
                }

                is Success -> {
                    onSuccess(state.data) // در صورت Success، داده را به تابع مربوطه می‌دهد
                }

                is Error -> {
                    onError(state.message) // در صورت Error، پیام خطا را به تابع مربوطه می‌دهد
                }
            }
        }
    }
}
