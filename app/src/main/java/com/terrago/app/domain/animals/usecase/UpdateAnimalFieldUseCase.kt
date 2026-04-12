package com.terrago.app.domain.animals.usecase

import com.terrago.app.domain.animals.AnimalsRepository
import javax.inject.Inject

class UpdateAnimalFieldUseCase @Inject constructor(
    private val repository: AnimalsRepository
) {

    fun setLastFeeding(id: Long) {
        repository.setLastFeeding(id)
    }

    fun setLastSpray(id: Long) {
        repository.setLastSpray(id)
    }

    fun setLastMolt(id: Long) {
        repository.setLastMolt(id)
    }

    fun setSize(id: Long, size: Long) {
        repository.setSize(id, size)
    }
}