package com.example.interesting_facts_about_numbers.functionality.data.src.di

import com.example.interesting_facts_about_numbers.functionality.abstraction.FactRepository
import com.example.interesting_facts_about_numbers.functionality.data.src.FactRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindRepository(impl: FactRepositoryImpl): FactRepository
}
