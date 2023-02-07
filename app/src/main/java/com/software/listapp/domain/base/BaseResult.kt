package com.software.listapp.domain.base

sealed class BaseResult <out T : Any, out U : Any> {
    data class Success <T: Any>(val data : T) : BaseResult<T, Nothing>()
    data class Error <U : Any>(val rawResponse: U) : BaseResult<Nothing, U>()
}

sealed class BaseResultList <out T: Any, out U: Any, out K: Any>{
    data class Success <T: Any, U: Any>(val data: T, val data2: U): BaseResultList<T, U, Nothing>()
    data class Error <K : Any>(val rawResponse: K) : BaseResultList<Nothing, Nothing, K>()
}

sealed class BaseResultTripleList <out T: Any, out U: Any,  out I: Any,   out K: Any>{
    data class Success <T: Any, U: Any,  I: Any>(val data: T, val data2: U, val data3: I): BaseResultTripleList<T, U, I, Nothing>()
    data class Error <K : Any>(val rawResponse: K) : BaseResultTripleList<Nothing, Nothing, Nothing, K>()
}