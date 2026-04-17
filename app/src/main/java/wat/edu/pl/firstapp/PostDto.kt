
package wat.edu.pl.firstapp

import com.squareup.moshi.JsonClass

//dane pobierane z JSON sa konwertowane na te zmienne tutaj
@JsonClass(generateAdapter = true)
data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String)