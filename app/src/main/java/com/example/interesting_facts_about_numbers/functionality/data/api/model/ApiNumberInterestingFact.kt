package com.example.interesting_facts_about_numbers.functionality.data.api.model

import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiNumberInterestingFact(
    @Json(name = "number") val number: String,
    @Json(name = "text") val text: String,
)

internal fun ApiNumberInterestingFact.toEntity(): NumberFact =
    NumberFact(
        number = number,
        text = text,
        id = 0,
    )
