package com.software.listapp.data.main.product

import android.content.Context
import androidx.room.Room
import com.software.listapp.data.comman.module.NetworkModule
import com.software.listapp.database.ProductDao
import com.software.listapp.database.RoomDatabase
import com.software.listapp.domain.main.product.ProductRepository
import com.software.listapp.utils.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ProductModule {
    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        return Room.databaseBuilder(
            appContext,
            RoomDatabase::class.java,
            "ListApp"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideProductDao(roomDatabase: RoomDatabase): ProductDao {
        return roomDatabase.productDao
    }


    @Singleton
    @Provides
    fun provideProductRepository(productApi: ProductApi,
                                 productDao: ProductDao,
                                 sharedPref: SharedPref): ProductRepository {
        return ProductRepositoryImp(productApi, productDao)
    }
}