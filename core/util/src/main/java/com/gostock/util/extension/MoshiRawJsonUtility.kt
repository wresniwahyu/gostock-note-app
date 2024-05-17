package com.gostock.util.extension

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.BufferedSource
import java.lang.reflect.Type

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class JsonString

class JsonStringJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (type != String::class.java) return null
        Types.nextAnnotations(annotations, JsonString::class.java) ?: return null
        return JsonStringJsonAdapter().nullSafe()
    }

    private class JsonStringJsonAdapter : JsonAdapter<String>() {
        override fun fromJson(reader: JsonReader): String =
            reader.nextSource().use(BufferedSource::readUtf8)

        override fun toJson(writer: JsonWriter, value: String?) {
            writer.valueSink().use { sink -> sink.writeUtf8(checkNotNull(value)) }
        }
    }
}