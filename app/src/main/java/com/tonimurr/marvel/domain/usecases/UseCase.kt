package com.tonimurr.marvel.domain.usecases

import com.tonimurr.marvel.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

abstract class UseCase<T> {

    open fun tagName(): String = this.javaClass.name

    fun baseFlow(invoke: suspend (flowCollector: FlowCollector<Resource<T>>) -> Unit): Flow<Resource<T>> = flow {
        try {
            invoke(this)
        }catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HttpException in ${tagName()}"))
        }catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "IOException in ${tagName()}"))
        }
    }

}