package com.example.interesting_facts_about_numbers.functionality.data.db

import androidx.room.*
import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact

@Dao
interface FactDao {
    @Query("SELECT * FROM number_fact")
    suspend fun getAllNumbers(): List<NumberFact>

    @Query("SELECT * FROM number_fact WHERE number = :number")
    suspend fun getNumberFactByNumber(number: String?): NumberFact

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(numberFact: NumberFact)

    @Delete
    suspend fun deleteNumber(numberFact: NumberFact)

}
