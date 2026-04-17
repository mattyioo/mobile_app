
package wat.edu.pl.firstapp

import retrofit2.Response
import retrofit2.http.GET
//zdefinowanie metody do pobrania danych z API
interface APIInterface {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}