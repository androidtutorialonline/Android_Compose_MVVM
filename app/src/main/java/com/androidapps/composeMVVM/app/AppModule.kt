package com.androidapps.composeMVVM.app

import android.content.Context
import androidx.room.Room
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
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    /*@Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApp {
        return app as MyApp
    }*/


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideItemDao(db: AppDatabase): ItemDao = db.itemDao()

    @Provides
    fun provideItemRepository(
        apiService: ApiService,
        itemDao: ItemDao,
    ): ItemRepository = ItemRepositoryImpl(apiService, itemDao)
}
