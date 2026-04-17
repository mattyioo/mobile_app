package wat.edu.pl.firstapp

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {
    //adres API
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    //pozwala uzywac Logcat
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    //tlumacz z JSON na obiekty z klasy PostDto
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    //fizyczne polaczenie HTTP
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    //lazy oznacze ze obiekt zostanie utworzony w momencie gdy bedzie potrzebny (pierwszy raz)
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    //tworzy obiekt API Interface
    val api: APIInterface by lazy {
        retrofit.create(APIInterface::class.java)
    }
}
