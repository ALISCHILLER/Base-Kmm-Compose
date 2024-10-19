package org.msa.basekmm.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.room.Room
import androidx.room.RoomDatabase
import org.msa.basekmm.data.database.NewsDatabase
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.UUID

// تابع برای اشتراک‌گذاری لینک در پلتفرم دسکتاپ (با کپی کردن آن به کلیپبورد)
actual fun shareLink(url: String) {
    // دریافت کلیپبورد سیستم با استفاده از Toolkit در جاوا
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard

    // قرار دادن URL به عنوان محتوای جدید در کلیپبورد
    clipboard.setContents(StringSelection(url), null)
}

// تولید یک UUID تصادفی و بازگرداندن آن به صورت رشته
actual fun randomUUIDStr(): String {
    // تولید UUID به وسیله UUID.randomUUID() که یک شناسه یکتا تولید می‌کند
    return UUID.randomUUID().toString()
}

// تابع برای مشخص کردن نوع پلتفرم که در اینجا دسکتاپ است
actual fun getType(): Type {
    // نوع دستگاه در اینجا Desktop بازگردانده می‌شود
    return Type.Desktop
}

// تابع برای دریافت اندازه صفحه در محیط Jetpack Compose (برای پلتفرم دسکتاپ)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenSize(): Size {
    // دریافت اطلاعات مربوط به اندازه پنجره فعلی
    val configuration = LocalWindowInfo.current

    // تبدیل ارتفاع و عرض به واحد dp (Density-independent Pixels)
    val screenHeightDp = configuration.containerSize.height.dp
    val screenWidthDP = configuration.containerSize.width.dp

    // بازگرداندن اندازه صفحه به صورت شیء Size
    return Size(width = screenWidthDP, height = screenHeightDp)
}

// تابع actual برای ایجاد سازنده دیتابیس Room برای NewsDatabase
actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    // تعریف فایل دیتابیس با استفاده از دایرکتوری موقت سیستم
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_Name)

    // شروع به ساخت سازنده دیتابیس Room
    return Room.databaseBuilder<NewsDatabase>(
        // نام دیتابیس که برابر با مسیر فایل دیتابیس است
        name = dbFile.absolutePath,
    )
}
