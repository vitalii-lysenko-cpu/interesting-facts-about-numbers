package com.example.interesting_facts_about_numbers.functionality.data.api.model

import com.example.interesting_facts_about_numbers.functionality.entity.Num
import com.example.interesting_facts_about_numbers.functionality.entity.NumberInterestingFact
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiNumberInterestingFact(
    @Json(name = "number") val num: String,
    @Json(name = "text") val text: String,
)

internal fun ApiNumberInterestingFact.toEntity(): NumberInterestingFact =
    NumberInterestingFact(
        num = Num(value = num),
        text = text,
    )
