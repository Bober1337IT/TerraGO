package com.terrago.app.ui.screens.animals.mock

import com.terrago.app.database.entity.AnimalPreview

object MockAnimals {

    val sampleAnimals = listOf(
        AnimalPreview(
            animalId = 1,
            animalName = "Kira",
            speciesLatinName = "P. metallica",
            objectName = "Terrarium #1",
            lastFeeding = "2025-01-10",
            lastSpray = "2025-01-09",
            lastMolt = "2024-11-02",
            photo = null
        ),
        AnimalPreview(
            animalId = 2,
            animalName = "Shadow",
            speciesLatinName = "T. stirmi",
            objectName = "Rack 3A",
            lastFeeding = "2025-01-08",
            lastSpray = "2025-01-06",
            lastMolt = "2024-10-26",
            photo = null
        ),
        AnimalPreview(
            animalId = 3,
            animalName = "Ruby",
            speciesLatinName = "B. hamorii",
            objectName = "Terrarium #5",
            lastFeeding = "2025-01-02",
            lastSpray = "2025-01-01",
            lastMolt = "2024-12-20",
            photo = null
        ),
        AnimalPreview(
            animalId = 4,
            animalName = "Ghost",
            speciesLatinName = "H. maculata",
            objectName = "Rack 1C",
            lastFeeding = "2025-01-11",
            lastSpray = "2025-01-10",
            lastMolt = "2024-09-15",
            photo = null
        )
    )
}
