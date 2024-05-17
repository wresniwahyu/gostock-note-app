package com.gostock.util.extension

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

internal class UUIDAdapter {

    @FromJson
    fun fromJsonToUUID(s: String?): UUID? {
        return UUID.fromString(s)
    }

    @ToJson
    fun toJsonFromUUID(uuid: UUID): String? {
        return uuid.toString()
    }

}