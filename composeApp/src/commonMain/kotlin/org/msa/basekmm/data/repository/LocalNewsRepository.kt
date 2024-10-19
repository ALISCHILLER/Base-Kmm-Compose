package org.msa.basekmm.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn
import org.msa.basekmm.data.database.dao.NewsDao
import org.msa.basekmm.data.model.Article

// کلاس LocalNewsRepository برای مدیریت عملیات محلی دیتابیس مربوط به مقالات خبری
class LocalNewsRepository(
    private val newsDao: NewsDao // دریافت DAO برای دسترسی به عملیات دیتابیس مقالات
) {
    // تابعی برای اضافه یا بروزرسانی یک مقاله در دیتابیس
    suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article) // فراخوانی متد upsert از NewsDao
    }

    // تابعی برای حذف یک مقاله از دیتابیس
    suspend fun deleteArticle(article: Article) {
        newsDao.delete(article) // فراخوانی متد delete از NewsDao
    }

    // تابعی برای حذف همه نشانک‌ها (bookmarks) از دیتابیس
    suspend fun deleteAllBookmark() {
        newsDao.deleteAllBookmarks() // فراخوانی متد deleteAllBookmark از NewsDao
    }

    // تابعی برای دریافت تمام مقالات از دیتابیس به صورت Flow
    fun getAllArticles() = newsDao.getAllArticles().flowOn(Dispatchers.IO)

    // تابعی برای دریافت یک مقاله خاص بر اساس شناسه آن
    suspend fun getArticle(articleId: String): Article? {
        return newsDao.getArticleById(articleId = articleId) // فراخوانی متد getArticle از NewsDao
    }
}
