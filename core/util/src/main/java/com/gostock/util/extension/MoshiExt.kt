package com.gostock.util.extension

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.reflect.KClass

internal const val TAG = "MoshiExtensions"

fun Moshi.Builder.baseMoshiBuilder(): Moshi.Builder {
    return this
        .addWithJavaClassAdapter()
        .add(KotlinJsonAdapterFactory())
        .add(JsonStringJsonAdapterFactory())
}

fun Moshi.Builder.addWithJavaClassAdapter(): Moshi.Builder {
    return this
        .add(UUIDAdapter())
        .add(GenericCollectionAdapterFactory(ArrayList::class.java) { ArrayList() })
}

inline fun <reified T : Any> T.toJson(customBuilder: Moshi.Builder = Moshi.Builder()): String =
    customBuilder
        .baseMoshiBuilder()
        .build()
        .adapter<T>(T::class.java).toJson(this)

// same as toJson above, but support non-reified type
fun <T : Any> T.toJson(klass: KClass<T>, customBuilder: Moshi.Builder = Moshi.Builder()): String =
    customBuilder
        .baseMoshiBuilder()
        .build()
        .adapter<T>(klass.java).toJson(this)


//For Non-collection class
inline fun <reified T : Any> String.fromJson(customBuilder: Moshi.Builder = Moshi.Builder()): T? =
    customBuilder
        .baseMoshiBuilder()
        .build()
        .adapter(T::class.java)
        .fromJson(this)

// same as fromJson above, but support non-reified type
fun <T : Any> String.fromJson(klass: KClass<T>, customBuilder: Moshi.Builder = Moshi.Builder()): T? =
    customBuilder
        .baseMoshiBuilder()
        .build()
        .adapter(klass.java)
        .fromJson(this)

inline fun <reified T : Any> String.toJsonObjectList(customBuilder: Moshi.Builder = Moshi.Builder()): List<T>? {
    return Types.newParameterizedType(List::class.java, T::class.java).let { type ->
        customBuilder
            .baseMoshiBuilder()
            .build()
            .adapter<List<T>>(type).fromJson(this)
    }
}


inline fun <reified T : Any> String.toJsonObjectArrayList(customBuilder: Moshi.Builder = Moshi.Builder()): ArrayList<T>? {
    return Types.newParameterizedType(ArrayList::class.java, T::class.java).let { type ->
        customBuilder
            .baseMoshiBuilder()
            .build()
            .adapter<ArrayList<T>>(type).fromJson(this)
    }
}


inline fun <reified T : Any> String.toJsonObjectMutableList(customBuilder: Moshi.Builder = Moshi.Builder()): MutableList<T>? {
    return Types.newParameterizedType(MutableList::class.java, T::class.java).let { type ->
        customBuilder
            .baseMoshiBuilder()
            .build()
            .adapter<MutableList<T>>(type).fromJson(this)
    }
}

inline fun <reified T : Any> String.toJsonObjectMutableMap(customBuilder: Moshi.Builder = Moshi.Builder()): MutableMap<T, T>? {
    return Types.newParameterizedType(MutableMap::class.java, T::class.java, T::class.java)
        .let { type ->
            customBuilder
                .baseMoshiBuilder()
                .build()
                .adapter<MutableMap<T, T>>(type).fromJson(this)
        }
}

