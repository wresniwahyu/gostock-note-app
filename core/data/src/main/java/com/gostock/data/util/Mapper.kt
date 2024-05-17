package com.gostock.data.util

interface Mapper<I, O> {
    fun map(input: I): O
}