package com.example.interesting_facts_about_numbers.functionality.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number_fact")
data class NumberFact(
    val number: String,
    val text: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)
