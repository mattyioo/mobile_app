
package wat.edu.pl.firstapp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String)