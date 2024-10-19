package org.msa.basekmm.data.database.dao


import androidx.room.* // وارد کردن کتابخانه Room برای مدیریت دیتابیس
import kotlinx.coroutines.flow.Flow // وارد کردن Flow برای استفاده در دیتابیس
import org.msa.basekmm.data.model.Article // وارد کردن مدل Article برای استفاده در DAO

// اینترفیس NewsDao برای عملیات دیتابیس روی مقالات
@Dao
interface NewsDao {

    /**
     * متدی برای اضافه کردن یا بروزرسانی یک مقاله.
     * در صورت وجود تداخل، مقاله جدید جایگزین می‌شود.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    /**
     * متدی برای حذف یک مقاله از دیتابیس.
     * این متد به صورت غیرهمزمان اجرا می‌شود.
     */
    @Delete
    suspend fun delete(article: Article)

    /**
     * متدی برای حذف تمام نشانک‌ها (bookmarks) از دیتابیس.
     * این متد به صورت غیرهمزمان اجرا می‌شود.
     */
    @Query("DELETE FROM articleTable")
    suspend fun deleteAllBookmarks()

    /**
     * متدی برای دریافت تمام مقالات به صورت Flow.
     * این متد به صورت همزمان اجرا می‌شود و تغییرات در مقالات را به صورت دنباله‌ای (stream) برمی‌گرداند.
     */
    @Query("SELECT * FROM articleTable")
    fun getAllArticles(): Flow<List<Article>>

    /**
     * متدی برای دریافت یک مقاله خاص بر اساس شناسه آن.
     * در صورت عدم وجود مقاله با شناسه مورد نظر، متد ممکن است null برگرداند.
     */
    @Query("SELECT * FROM articleTable WHERE articleId = :articleId")
    suspend fun getArticleById(articleId: String): Article?
}
