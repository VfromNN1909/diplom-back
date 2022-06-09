package me.vlasoff.diplombackend.utils

import kotlinx.coroutines.*

suspend fun <A, B> Iterable<A>.pMap(block: suspend (A) -> B): List<B> = coroutineScope {
    return@coroutineScope map { async { block(it) } }.awaitAll()
}

fun getNumber(string: String): Int? {
    return try {
        string.replace("\\D".toRegex(), "").toInt()
    } catch (ex: Exception) {
        null
    }
}