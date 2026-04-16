
package wat.edu.pl.firstapp

import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}