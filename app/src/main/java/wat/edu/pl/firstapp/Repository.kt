package wat.edu.pl.firstapp


class Repository(
    private val api: APIInterface = RetrofitProvider.api
) {

    suspend fun getData(): List<PostDto> {
        return api.getPosts()
    }
}
