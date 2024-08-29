package com.androidapps.composeMVVM.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.androidapps.composeMVVM.BuildConfig
import com.androidapps.composeMVVM.data.ApiService
import com.androidapps.composeMVVM.data.database.AppDatabase
import com.androidapps.composeMVVM.data.database.ItemDao
import com.androidapps.composeMVVM.data.repository.ItemRepositoryImpl
import com.androidapps.composeMVVM.domain.ItemRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


/**
 * This is a Dagger Hilt module that provides application-wide dependencies.
 * The `@Module` annotation indicates that this class is a Dagger module.
 * The `@InstallIn(SingletonComponent::class)` ensures that the provided dependencies have application-wide scope.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of Moshi for JSON parsing and serialization.
     * Moshi is configured with the `KotlinJsonAdapterFactory` to handle Kotlin data classes.
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    /**
     * Configures and returns a singleton instance of OkHttpClient.
     * The client includes an interceptor for adding headers and logs HTTP request/response data in debug mode.
     */
    private fun getRetrofitClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }


    /*@Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApp {
        return app as MyApp
    }*/

    /**
     * Provides a singleton instance of the ApiService for making network requests.
     * The Retrofit instance uses the base URL of GitHub's API and integrates Moshi for JSON conversion.
     */
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val moshiBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl("https://api.github.com")

            .client(getRetrofitClient())
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
            .build()
            .create(ApiService::class.java)
    }

    /**
     * Provides a singleton instance of the AppDatabase using Room.
     * The database is built using the application context and is named "app_database".
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    /**
     * Provides an instance of the ItemDao from the AppDatabase.
     * The ItemDao is used to access data operations on the Item table in the Room database.
     */
    @Provides
    fun provideItemDao(db: AppDatabase): ItemDao = db.itemDao()

    /**
     * Provides a singleton instance of ItemRepository.
     * The repository implementation (ItemRepositoryImpl) requires the ApiService, ItemDao, and Application context for its dependencies.
     */
    @Provides
    fun provideItemRepository(
        apiService: ApiService,
        itemDao: ItemDao,
        context: Application,
    ): ItemRepository = ItemRepositoryImpl(apiService, itemDao, context)

    /*@Provides
    fun provideCucumberRepository(
        apiService: ApiService,
        itemDao: ItemDao,
    ): CucumberRepository = CucumberRepositoryImpl(apiService, itemDao)*/
}
