package com.gostock.util.extension

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class GenericCollectionAdapterFactory<TCollection : MutableCollection<*>>(
    private val collectionClazz: Class<TCollection>,
    private val createEmptyCollection: () -> MutableCollection<Any>
) : JsonAdapter.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        val paramType = type as? ParameterizedType ?: return null

        //Ex: Raw type string:class java.util.ArrayList, collectionClazz string:class java.util.ArrayList
        if (paramType.rawType.toString() != collectionClazz.toString()) return null
        if (paramType.actualTypeArguments.size != 1) return null

        //Ex: String for the actualTypeArguments[0] string id.mapan.social.models.banner.CampaignItem
        val argTypeName = paramType.actualTypeArguments[0].toString().split(" ")[1]
        Log.d(
            TAG,
            "argTypeName:${argTypeName}, Raw Type:${paramType.rawType.toString()}, Clazz:${collectionClazz.toString()} "
        )

        val argType = Class.forName(argTypeName) as Class<Any>

        return GenericCollectionAdapter(argType, moshi, createEmptyCollection)
    }
}

