package com.software.listapp.data.comman.module

import android.content.Context
import com.software.listapp.utils.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context) : SharedPref {
        return SharedPref(context)
    }
}