package com.being.coder.app.nw.domain.usecase

import com.being.coder.app.nw.domain.repository.CurrentConditionRepository

class GetCurrentConditionUseCase(
    private val repository: CurrentConditionRepository
) {
    suspend operator fun invoke(city: String) = repository.getCurrentCondition(city)
}