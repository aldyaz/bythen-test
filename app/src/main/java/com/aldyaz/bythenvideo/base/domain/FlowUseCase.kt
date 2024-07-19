package com.aldyaz.bythenvideo.base.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<PARAM, RESULT> {

    abstract fun execute(param: PARAM): Flow<ResultState<RESULT>>

    operator fun invoke(param: PARAM) = execute(param)

}