package com.terrago.app.domain

import com.terrago.app.database.repositories.AnimalsRepository

class UpdateAnimalFieldUseCase(
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