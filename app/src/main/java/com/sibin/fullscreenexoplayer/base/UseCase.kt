package com.sibin.fullscreenexoplayer.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**Created by Sibin E A on 18,September,2020 sibinea1@gmail.com **/

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type
    operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Type) -> Unit = {}
    ) {
        val backgroundJob = scope.async(Dispatchers.IO) { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }

    class None
}