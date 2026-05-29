package com.terrago.app.domain.species.usecase

import com.terrago.app.domain.species.SpeciesRepository
import com.terrago.app.domain.species.model.Species
import javax.inject.Inject

class UpsertSpeciesUserCase @Inject constructor(
    private val repository: SpeciesRepository
) {
    suspend operator fun invoke(species: Species): Long =
        if (species.id == 0L) {
            repository.insertSpecies(species)
        } else {
            repository.updateSpecies(species)
            species.id
        }
}
