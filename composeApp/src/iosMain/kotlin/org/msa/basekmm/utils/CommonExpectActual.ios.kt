package org.msa.basekmm.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.room.Room
import androidx.room.RoomDatabase
import org.msa.basekmm.data.database.NewsDatabase
import platform.UIKit.*
import platform.Foundation.NSUUID
import platform.Foundation.NSHomeDirectory

// تابع به‌اشتراک‌گذاری لینک برای iOS
actual fun shareLink(url: String) {
    // دریافت ViewController فعلی
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController

    // ایجاد Activity View Controller برای به‌اشتراک‌گذاری لینک
    val activityViewController = UIActivityViewController(listOf(url), null)

    // بررسی نال بودن currentViewController برای جلوگیری از کرش
    currentViewController?.let {
        it.presentViewController(activityViewController, animated = true, completion = null)
    } ?: run {
        println("Error: No current view controller found.")
    }
}

// تولید یک UUID تصادفی و بازگرداندن آن به صورت رشته
actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}

// تابعی که نوع دستگاه را باز می‌گرداند، در اینجا Mobile برگردانده می‌شود
actual fun getType(): Type {
    return Type.Mobile
}

// تابع برای دریافت اندازه صفحه نمایش در محیط Jetpack Compose
@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenSize(): Size {
    // دریافت اطلاعات پنجره فعلی
    val configuration = LocalWindowInfo.current

    // محاسبه عرض و ارتفاع صفحه به واحد dp
    val screenHeightDp = configuration.containerSize.height.dp
    val screenWidthDP = configuration.containerSize.width.dp

    // بررسی اگر اندازه‌ها نادرست باشند، یک هشدار چاپ می‌شود
    if (screenHeightDp.value == 0f || screenWidthDP.value == 0f) {
        println("Warning: Screen size could not be determined.")
    }

    // بازگرداندن اندازه صفحه به عنوان شیء Size
    return Size(width = screenWidthDP, height = screenHeightDp)
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    // تعریف مسیر فایل دیتابیس با استفاده از دایرکتوری خانگی اپلیکیشن
    val dbFilePath = NSHomeDirectory() + "/$DB_Name"

    // شروع به ساخت سازنده دیتابیس Room
    return Room.databaseBuilder<NewsDatabase>(
        // نام دیتابیس که برابر با مسیر فایل دیتابیس است
        name = dbFilePath,
        // تابع لامبدا برای ایجاد نمونه از NewsDatabase
        factory = { NewsDatabase::class.instantiateImpl() }
    )
}