package com.bashkir.a22bytenews.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatToString(): String =
    "${toLocalTime().formatToString()} ${toLocalDate().formatToString()}"

fun LocalDate.formatToString(): String =
    "${withZero(dayOfMonth)}.${withZero(monthValue)}.$year"

fun LocalTime.formatToString(): String =
    "${withZero(hour)}:${withZero(minute)}"

private fun withZero(num: Int): String =
    if (num.toString().length == 1) "0$num" else num.toString()

class LocalDateTimeJsonAdapter : TypeAdapter<LocalDateTime>() {
    override fun write(out: JsonWriter, value: LocalDateTime?) {
        out.value(value.toString())
    }

    override fun read(`in`: JsonReader): LocalDateTime {
        val text = `in`.nextString()
        val parsableText = text.substring(
            0,
            text.lastIndexOf(
                when {
                    text.contains('.') -> '.'
                    text.contains('+') -> '+'
                    else -> 'Z'
                }
            )
        )

        return LocalDateTime.parse(
            parsableText, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        )
    }
}