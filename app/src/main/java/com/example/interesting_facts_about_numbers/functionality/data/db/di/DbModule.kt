package com.example.interesting_facts_about_numbers.functionality.data.db.di

import android.content.Context
import androidx.room.Room
import com.example.interesting_facts_about_numbers.functionality.data.db.DataBase
import com.example.interesting_facts_about_numbers.functionality.data.db.DbDataSource
import com.example.interesting_facts_about_numbers.functionality.data.db.DbDataSourceImpl
import com.example.interesting_facts_about_numbers.functionality.data.db.FactDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface DbModule {

    @Binds
    fun bindApiDataSource(impl: DbDataSourceImpl): DbDataSource

    companion object {

        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext context: Context): DataBase {
            return Room.databaseBuilder(context, DataBase::class.java, "app-db")
                .build()
        }

        @Provides
        fun provideNumberFactDao(database: DataBase): FactDao {
            return database.factDao()
        }
    }
}
