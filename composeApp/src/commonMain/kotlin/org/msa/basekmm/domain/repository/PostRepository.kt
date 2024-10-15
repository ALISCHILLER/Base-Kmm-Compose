package org.msa.basekmm.domain.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.msa.basekmm.domain.model.PostModel

class PostRepository(private val httpClient: HttpClient) {

    suspend fun getPosts(): List<PostModel> {
        return httpClient.get("https://jsonplaceholder.typicode.com/posts").body()
    }

    suspend fun get(id: Long): PostModel {
        return httpClient.get("https://jsonplaceholder.typicode.com/posts").body()
    }

}