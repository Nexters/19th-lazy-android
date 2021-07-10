package com.nexters.domain.usecase.base

interface BaseUseCase<Params, Result>{
    fun buildUseCase(params: Params) : Result
}