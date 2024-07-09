package com.being.coder.app.nw.domain.repository

import com.being.coder.app.nw.data.dto.CurrentConditionDto
import com.being.coder.app.nw.util.Response
import kotlinx.coroutines.flow.Flow

interface CurrentConditionRepository {
    suspend fun getCurrentCondition(city: String): Flow<Response<CurrentConditionDto>>
}