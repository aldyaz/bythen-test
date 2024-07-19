package com.aldyaz.bythenvideo.base.domain

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface CoroutinesContextProvider {

    val io: CoroutineContext
    val main: CoroutineContext

    object Default : CoroutinesContextProvider {

        override val io: CoroutineContext
            get() = Dispatchers.IO

        override val main: CoroutineContext
            get() = Dispatchers.Main
    }

}