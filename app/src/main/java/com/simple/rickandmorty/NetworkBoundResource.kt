package com.simple.rickandmorty

import com.simple.rickandmorty.domain.entity.LoadStatus
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(LoadStatus.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { LoadStatus.Success(it) }
        } catch (exception: Exception) {
            query().map { LoadStatus.Error(exception.localizedMessage, it) }
        }
    } else {
        query().map { LoadStatus.Success(it) }
    }

    emitAll(flow)
}