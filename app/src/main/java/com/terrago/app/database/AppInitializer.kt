package com.terrago.app.database

import android.content.Context
import com.terrago.app.database.repositories.SpeciesRepository
import com.terrago.app.db.Species
import kotlinx.coroutines.flow.first

class AppInitializer(
    private val context: Context,
    private val speciesRepository: SpeciesRepository
) {
    private val prefs = context.getSharedPreferences("terra_go_prefs", Context.MODE_PRIVATE)

    suspend fun initialize() {
        // Check if initialization has already happened to avoid unnecessary DB queries
        if (!prefs.getBoolean("is_species_prepopulated", false)) {
            val existingSpecies = speciesRepository.getAllSpecies().first()
            if (existingSpecies.isEmpty()) {
                prepopulateSpecies()
            }
            // Mark as initialized so it only runs once ever
            prefs.edit().putBoolean("is_species_prepopulated", true).apply()
        }
    }

    private suspend fun prepopulateSpecies() {
        val defaultSpecies = listOf(
            Species(
                species_id = 0,
                name_latin = "Pogona vitticeps",
                name_common = "Bearded Dragon",
                description = "Diurnal, terrestrial lizard native to Australia. Hardy, intelligent, and one of the most commonly kept beginner reptiles.",
                temperature_min = 24.0,
                temperature_max = 32.0,
                humidity_min = 30.0,
                humidity_max = 40.0,
                light_cycle_h = 12
            ),
            Species(
                species_id = 1,
                name_latin = "Eublepharis macularius",
                name_common = "Leopard Gecko",
                description = "Nocturnal, terrestrial gecko from arid regions. Hardy and widely kept; suitable for beginners.",
                temperature_min = 22.0,
                temperature_max = 32.0,
                humidity_min = 30.0,
                humidity_max = 40.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 2,
                name_latin = "Correlophus ciliatus",
                name_common = "Crested Gecko",
                description = "Nocturnal, arboreal gecko from New Caledonia. Does not require high heat; popular beginner species.",
                temperature_min = 20.0,
                temperature_max = 26.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 3,
                name_latin = "Anolis carolinensis",
                name_common = "Green Anole",
                description = "Diurnal, arboreal lizard native to the southeastern United States. Active and visually oriented.",
                temperature_min = 22.0,
                temperature_max = null,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),
            Species(
                species_id = 4,
                name_latin = "Phelsuma spp.",
                name_common = "Day Gecko",
                description = "Diurnal, arboreal geckos from Madagascar. Brightly colored and require high-intensity lighting.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 5,
                name_latin = "Gekko gecko",
                name_common = "Tokay Gecko",
                description = "Nocturnal, arboreal gecko native to Southeast Asia. Large, territorial, and vocal.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 6,
                name_latin = "Rhacodactylus auriculatus",
                name_common = "Gargoyle Gecko",
                description = "Nocturnal, arboreal gecko from New Caledonia. Sensitive to heat; avoid temperatures above 27°C.",
                temperature_min = 20.0,
                temperature_max = 25.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 7,
                name_latin = "Pachydactylus rangei",
                name_common = "Web-foot Gecko",
                description = "Nocturnal, terrestrial gecko. Requires dry substrate with high humidity spikes at night.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 30.0,
                humidity_max = 50.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 8,
                name_latin = "Uromastyx spp.",
                name_common = "Uromastyx",
                description = "Diurnal, terrestrial herbivore. Needs very low humidity and extreme heat gradients.",
                temperature_min = 26.0,
                temperature_max = 35.0,
                humidity_min = 10.0,
                humidity_max = 30.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 9,
                name_latin = "Tiliqua spp.",
                name_common = "Blue-Tongued Skink",
                description = "Diurnal, terrestrial. Humidity needs vary (40% for Australian, 70% for Indonesian species).",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 75.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 10,
                name_latin = "Chlamydosaurus kingii",
                name_common = "Frilled Lizard",
                description = "Diurnal, arboreal to semi-terrestrial. Requires large enclosures and high heat.",
                temperature_min = 24.0,
                temperature_max = 32.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 11,
                name_latin = "Crotaphytus collaris",
                name_common = "Collared Lizard",
                description = "Diurnal, terrestrial. Extremely active; requires high UV and desert-like heat.",
                temperature_min = 24.0,
                temperature_max = 32.0,
                humidity_min = 20.0,
                humidity_max = 40.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 12,
                name_latin = "Physignathus cocincinus",
                name_common = "Water Dragon",
                description = "Diurnal, semi-arboreal. Needs a large swimming area and constant high humidity.",
                temperature_min = 24.0,
                temperature_max = 29.0,
                humidity_min = 70.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 13,
                name_latin = "Chamaeleo calyptratus",
                name_common = "Veiled Chameleon",
                description = "Diurnal, arboreal. Requires screened enclosures for high airflow to prevent infections.",
                temperature_min = 22.0,
                temperature_max = 30.0,
                humidity_min = 40.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 14,
                name_latin = "Furcifer pardalis",
                name_common = "Panther Chameleon",
                description = "Diurnal, arboreal. Highly dependent on hydration through misting or drippers.",
                temperature_min = 22.0,
                temperature_max = 26.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 15,
                name_latin = "Lepidophyma flavimaculatum",
                name_common = "Yellow-Spotted Night Lizard",
                description = "Nocturnal, terrestrial/saxicolous. Prefers cool, damp, and dark microclimates.",
                temperature_min = 20.0,
                temperature_max = 26.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 16,
                name_latin = "Scincus scincus",
                name_common = "Sandfish Skink",
                description = "Diurnal, fossorial desert specialist. Requires deep, fine sand to burrow.",
                temperature_min = 24.0,
                temperature_max = 32.0,
                humidity_min = 10.0,
                humidity_max = 30.0,
                light_cycle_h = 12
            ),
            Species(
                species_id = 17,
                name_latin = "Lepidodactylus lugubris",
                name_common = "Mourning Gecko",
                description = "Nocturnal, arboreal gecko. Parthenogenetic; tolerant of moderate temperatures and humidity.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 18,
                name_latin = "Uroplatus fimbriatus",
                name_common = "Giant Leaf-tailed Gecko",
                description = "Nocturnal, arboreal gecko from Madagascar. Requires high humidity and excellent ventilation.",
                temperature_min = 20.0,
                temperature_max = 26.0,
                humidity_min = 70.0,
                humidity_max = 90.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 19,
                name_latin = "Coleonyx variegatus",
                name_common = "Western Banded Gecko",
                description = "Nocturnal, terrestrial gecko. Prefers rocky microhabitats and moderate humidity.",
                temperature_min = 20.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 20,
                name_latin = "Pantherophis guttatus",
                name_common = "Corn Snake",
                description = "Nocturnal/crepuscular, terrestrial snake. Hardy and popular beginner species.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 50.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 21,
                name_latin = "Python regius",
                name_common = "Ball Python",
                description = "Nocturnal, terrestrial snake. Requires higher humidity to prevent respiratory issues.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 22,
                name_latin = "Gongylophis colubrinus",
                name_common = "Kenyan Sand Boa",
                description = "Nocturnal, fossorial snake. Needs sandy substrate and relatively dry air.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 30.0,
                humidity_max = 50.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 23,
                name_latin = "Eryx johnii",
                name_common = "Indian Sand Boa",
                description = "Nocturnal, fossorial snake. Requires dry substrate with occasional moderate humidity.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 30.0,
                humidity_max = 50.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 24,
                name_latin = "Lampropeltis getula",
                name_common = "Eastern Kingsnake",
                description = "Diurnal/nocturnal, terrestrial snake. Hardy and adaptable to moderate humidity.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 25,
                name_latin = "Lampropeltis triangulum",
                name_common = "Milk Snake",
                description = "Nocturnal, terrestrial snake. Needs moderate humidity and stable temperatures.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 26,
                name_latin = "Thamnophis sirtalis",
                name_common = "Common Garter Snake",
                description = "Diurnal/nocturnal, semi-aquatic snake. Tolerates variable humidity and temperatures.",
                temperature_min = 20.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 27,
                name_latin = "Morelia spilota",
                name_common = "Carpet Python",
                description = "Nocturnal, semi-arboreal snake. Prefers moderate humidity and climbing opportunities.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 28,
                name_latin = "Lichanura trivirgata",
                name_common = "Rosy Boa",
                description = "Nocturnal, terrestrial snake. Desert-adapted; low humidity is essential to prevent rot.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 20.0,
                humidity_max = 40.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 29,
                name_latin = "Boa constrictor",
                name_common = "Boa Constrictor",
                description = "Nocturnal, semi-arboreal snake. Prefers moderate humidity and stable warmth.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 60.0,
                humidity_max = 75.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 30,
                name_latin = "Dendrobates tinctorius",
                name_common = "Dyeing Poison Dart Frog",
                description = "Diurnal, terrestrial. Cannot tolerate high heat.",
                temperature_min = 21.0,
                temperature_max = 26.0,
                humidity_min = 70.0,
                humidity_max = 95.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 31,
                name_latin = "Hyla cinerea",
                name_common = "Green Tree Frog",
                description = "Nocturnal/crepuscular, arboreal. Prefers moderate humidity and ambient warmth.",
                temperature_min = 20.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 32,
                name_latin = "Ceratophrys cranwelli",
                name_common = "Cranwell's Pacman Frog",
                description = "Nocturnal, terrestrial frog. Requires high humidity and soft, damp substrate.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 33,
                name_latin = "Kaloula pulchra",
                name_common = "Banded Bullfrog",
                description = "Nocturnal, terrestrial/fossorial frog. Prefers moist substrate to burrow into.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 34,
                name_latin = "Lithobates pipiens",
                name_common = "Northern Leopard Frog",
                description = "Diurnal/nocturnal, semi-aquatic frog. Prefers cooler, oxygenated environments.",
                temperature_min = 15.0,
                temperature_max = 24.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 35,
                name_latin = "Bombina orientalis",
                name_common = "Fire-Bellied Toad",
                description = "Diurnal, semi-aquatic toad. Needs moderate temperatures; temperatures over 26°C are stressful.",
                temperature_min = 16.0,
                temperature_max = 24.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 36,
                name_latin = "Anaxyrus americanus",
                name_common = "American Toad",
                description = "Nocturnal, terrestrial. Hardy; requires loose soil and a water dish.",
                temperature_min = 15.0,
                temperature_max = 25.0,
                humidity_min = 40.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 37,
                name_latin = "Ambystoma mexicanum",
                name_common = "Axolotl",
                description = "Fully aquatic salamander. Water must remain cool; temperatures above 22°C are dangerous.",
                temperature_min = 15.0,
                temperature_max = 19.0,
                humidity_min = null,
                humidity_max = null,
                light_cycle_h = 12
            ),

            Species(
                species_id = 38,
                name_latin = "Notophthalmus viridescens",
                name_common = "Eastern Newt",
                description = "Semi-aquatic. Requires clean water and a land area for the 'eft' stage.",
                temperature_min = 16.0,
                temperature_max = 22.0,
                humidity_min = 70.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 39,
                name_latin = "Ambystoma mavortium",
                name_common = "Tiger Salamander",
                description = "Terrestrial/fossorial. Needs deep, moist substrate to burrow and moderate temperatures.",
                temperature_min = 16.0,
                temperature_max = 22.0,
                humidity_min = 70.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 40,
                name_latin = "Brachypelma hamorii",
                name_common = "Mexican Redknee Tarantula",
                description = "Terrestrial, docile tarantula. Desert/scrubland species; keep substrate mostly dry.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 41,
                name_latin = "Grammostola pulchripes",
                name_common = "Chaco Golden Knee Tarantula",
                description = "Terrestrial, calm tarantula. Known for its large size and golden leg markings.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 42,
                name_latin = "Avicularia avicularia",
                name_common = "Pink Toe Tarantula",
                description = "Arboreal tarantula. Requires high humidity combined with high cross-ventilation.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 43,
                name_latin = "Nhandu chromatus",
                name_common = "Red and White Striped Tarantula",
                description = "Terrestrial tarantula. Fast-growing and often defensive; prefers slightly damp substrate.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 75.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 44,
                name_latin = "Acanthoscurria geniculata",
                name_common = "Brazilian Whiteknee Tarantula",
                description = "Terrestrial tarantula. Very large and opportunistic eater; keep humidity moderate.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 75.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 45,
                name_latin = "Pandinus imperator",
                name_common = "Emperor Scorpion",
                description = "Nocturnal, terrestrial scorpion. Requires deep substrate and high humidity to prevent molting issues.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 75.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),
            Species(
                species_id = 46,
                name_latin = "Hadrurus arizonensis",
                name_common = "Desert Hairy Scorpion",
                description = "Largest North American scorpion; known for aggressive hunting and burrowing deep into sandy substrates.",
                temperature_min = 24.0,
                temperature_max = 35.0,
                humidity_min = 10.0,
                humidity_max = 30.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 47,
                name_latin = "Mastigoproctus giganteus",
                name_common = "Vinegaroon (Whip Scorpion)",
                description = "Lacks venom but can spray acetic acid from its tail; uses heavy pedipalps for crushing prey.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 48,
                name_latin = "Damon medius",
                name_common = "Tailless Whip Scorpion",
                description = "Flat-bodied arachnid with elongated front legs used as sensory feelers; famous for its appearance in popular media.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 75.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 49,
                name_latin = "Centruroides sculpturatus",
                name_common = "Arizona Bark Scorpion",
                description = "Highly agile climber often found on trees or walls; possesses a potent neurotoxic sting.",
                temperature_min = 24.0,
                temperature_max = 32.0,
                humidity_min = 20.0,
                humidity_max = 40.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 50,
                name_latin = "Hierodula membranacea",
                name_common = "Giant Asian Mantis",
                description = "Aggressive predator capable of taking down large insects and small vertebrates; popular for its bold personality.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 51,
                name_latin = "Phyllocrania paradoxa",
                name_common = "Ghost Mantis",
                description = "Expertly mimicks dried leaves with leafy protrusions on the head and limbs; communal if food is plentiful.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 52,
                name_latin = "Mantis religiosa",
                name_common = "European Mantis",
                description = "Highly adaptable species with a distinctive black spot on the inner front legs used for threat displays.",
                temperature_min = 18.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 53,
                name_latin = "Extatosoma tiaratum",
                name_common = "Giant Prickly Stick Insect",
                description = "Covered in thorn-like spikes and curls its tail like a scorpion to ward off predators; feeds on eucalyptus and bramble.",
                temperature_min = 20.0,
                temperature_max = 26.0,
                humidity_min = 60.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 54,
                name_latin = "Phyllium pulchrifolium",
                name_common = "Walking Leaf",
                description = "Incredible leaf mimicry including 'brown spots' and ragged edges; moves with a swaying gait to simulate wind-blown foliage.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 55,
                name_latin = "Hymenopus coronatus",
                name_common = "Orchid Mantis",
                description = "Females look like flowers to lure pollinators; males are much smaller and reach maturity significantly faster.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 56,
                name_latin = "Hierodula patellifera",
                name_common = "Giant Rainforest Mantis",
                description = "Sturdy, green-colored predator that thrives in lush foliage; requires cross-ventilation to prevent stagnant air.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 57,
                name_latin = "Platymeris rhadamanthus",
                name_common = "Red-Spot Assassin Bug",
                description = "Injects liquefying enzymes into prey via a sharp proboscis; capable of spitting venom defensively.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 58,
                name_latin = "Asbolus verrucosus",
                name_common = "Blue Death Feigning Beetle",
                description = "Coated in a blue waxy secretion to prevent dehydration; known for 'playing dead' when startled.",
                temperature_min = 22.0,
                temperature_max = 30.0,
                humidity_min = 10.0,
                humidity_max = 25.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 59,
                name_latin = "Archispirostreptus gigas",
                name_common = "Giant African Millipede",
                description = "Possesses hundreds of legs and secretes a mild irritating liquid if threatened; vital for breaking down leaf litter.",
                temperature_min = 22.0,
                temperature_max = 26.0,
                humidity_min = 70.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 60,
                name_latin = "Scolopendra dehaani",
                name_common = "Vietnam Giant Centipede",
                description = "Extremely fast and aggressive predator with a painful venomous bite; requires a highly secure escape-proof enclosure.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 61,
                name_latin = "Lissachatina fulica",
                name_common = "Giant African Land Snail",
                description = "Voracious eater of vegetation; requires cuttlefish bone or similar sources for shell growth.",
                temperature_min = 20.0,
                temperature_max = 26.0,
                humidity_min = 75.0,
                humidity_max = 90.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 62,
                name_latin = "Coenobita clypeatus",
                name_common = "Caribbean Hermit Crab",
                description = "Social scavenger that must periodically trade shells; requires deep substrate for molting underground.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 75.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 63,
                name_latin = "Porcellio scaber",
                name_common = "Common Rough Isopod",
                description = "Hardy scavenger that consumes mold, decaying wood, and animal waste; excellent for larger bioactive setups.",
                temperature_min = 18.0,
                temperature_max = 26.0,
                humidity_min = 60.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 64,
                name_latin = "Folsomia candida",
                name_common = "White Springtails",
                description = "Essential micro-scavenger that prevents fungal outbreaks by consuming mold and spores in damp soil.",
                temperature_min = 18.0,
                temperature_max = 28.0,
                humidity_min = 80.0,
                humidity_max = 95.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 65,
                name_latin = "Trichorhina tomentosa",
                name_common = "Dwarf White Isopod",
                description = "Tiny, soft-bodied clean-up crew that stays mostly underground; ideal for tropical vivariums.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 75.0,
                humidity_max = 90.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 66,
                name_latin = "Anadenobolus monilicornis",
                name_common = "Bumblebee Millipede",
                description = "Visually striking detritivore that thrives in moist leaf litter and rotting wood layers.",
                temperature_min = 22.0,
                temperature_max = 27.0,
                humidity_min = 75.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 67,
                name_latin = "Eisenia fetida",
                name_common = "Red Wiggler Worm",
                description = "Prodigious composter that processes organic matter into nutrient-rich castings for terrarium plants.",
                temperature_min = 15.0,
                temperature_max = 25.0,
                humidity_min = 70.0,
                humidity_max = 90.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 68,
                name_latin = "Deroceras laeve",
                name_common = "Marsh Slug",
                description = "Moisture-dependent gastropod that thrives in heavily planted, swampy environments.",
                temperature_min = 15.0,
                temperature_max = 22.0,
                humidity_min = 85.0,
                humidity_max = 95.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 69,
                name_latin = "Subulina octona",
                name_common = "Miniature Awl Snail",
                description = "Rapidly breeding scavenger that aids in recycling nutrients in tropical forest floor setups.",
                temperature_min = 20.0,
                temperature_max = 26.0,
                humidity_min = 70.0,
                humidity_max = 90.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 70,
                name_latin = "Porcellio laevis",
                name_common = "Dairy Cow Isopod",
                description = "Large, fast-moving scavenger with a high protein appetite; frequently used as a clean-up crew for large reptiles.",
                temperature_min = 20.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 71,
                name_latin = "Armadillidium maculatum",
                name_common = "Zebra Isopod",
                description = "Distinctive black and white striped species that prefers to congregate under pieces of cork bark.",
                temperature_min = 20.0,
                temperature_max = 26.0,
                humidity_min = 70.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 72,
                name_latin = "Grammostola rosea",
                name_common = "Chilean Rose Hair Tarantula",
                description = "Known for its low maintenance and calm demeanor; goes through long periods of fasting and inactivity.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),
            Species(
                species_id = 73,
                name_latin = "Tliltocatl albopilosus",
                name_common = "Curly Hair Tarantula",
                description = "Distinctive for its unique 'wool-like' bristles; exceptionally calm and frequently stays out in the open.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 74,
                name_latin = "Chromatopelma cyaneopubescens",
                name_common = "Green Bottle Blue Tarantula",
                description = "Heavy webber that creates intricate silk tunnels; famous for its vibrant orange abdomen and blue legs.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 40.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 75,
                name_latin = "Aphonopelma chalcodes",
                name_common = "Arizona Blonde Tarantula",
                description = "Slow-moving desert dweller with a high tolerance for heat; females are known to live for over 30 years.",
                temperature_min = 24.0,
                temperature_max = 32.0,
                humidity_min = 20.0,
                humidity_max = 40.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 76,
                name_latin = "Cyriopagopus lividus",
                name_common = "Cobalt Blue Tarantula",
                description = "Shy but highly defensive fossorial species; its brilliant blue coloration is only visible under specific lighting.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 77,
                name_latin = "Theraphosa blondi",
                name_common = "Goliath Birdeater",
                description = "The world's largest tarantula by mass; produces audible 'hissing' sounds through stridulation when threatened.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 90.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 78,
                name_latin = "Lasiodora parahybana",
                name_common = "Salmon Pink Birdeater",
                description = "Grows at an incredible rate and possesses a voracious feeding response; frequently kicks urticating hairs if disturbed.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 65.0,
                humidity_max = 75.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 79,
                name_latin = "Pelinobius muticus",
                name_common = "King Baboon Tarantula",
                description = "Extremely slow-growing African species with thickened rear legs used for digging deep, permanent burrows.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 80,
                name_latin = "Heterometrus silenus",
                name_common = "Asian Forest Scorpion",
                description = "Large, glossy black scorpion often confused with the Emperor Scorpion but features more slender pincers.",
                temperature_min = 24.0,
                temperature_max = 30.0,
                humidity_min = 75.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 81,
                name_latin = "Heterometrus spinifer",
                name_common = "Giant Forest Scorpion",
                description = "Tropical predator that uses its massive pedipalps rather than its sting to dispatch prey; thrives in leaf litter.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 75.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 82,
                name_latin = "Leiurus quinquestriatus",
                name_common = "Deathstalker Scorpion",
                description = "One of the most dangerous scorpions in the world; fast-moving and possesses a complex, potent neurotoxic venom.",
                temperature_min = 26.0,
                temperature_max = 35.0,
                humidity_min = 10.0,
                humidity_max = 25.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 83,
                name_latin = "Paravaejovis spinigerus",
                name_common = "Stripe-Tailed Scorpion",
                description = "Small, hardy scorpion found under rocks; easily identified by the dark stripes on the dorsal side of its tail.",
                temperature_min = 24.0,
                temperature_max = 32.0,
                humidity_min = 20.0,
                humidity_max = 35.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 84,
                name_latin = "Pandinus imperator",
                name_common = "Black Emperor Scorpion",
                description = "A classic pet species known for its heavy armor and fluorescent glow under ultraviolet light.",
                temperature_min = 24.0,
                temperature_max = 28.0,
                humidity_min = 75.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 85,
                name_latin = "Phidippus audax",
                name_common = "Bold Jumping Spider",
                description = "Highly intelligent spider with excellent vision; known for performing complex 'dances' and interactive hunting.",
                temperature_min = 20.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 86,
                name_latin = "Hogna carolinensis",
                name_common = "Carolina Wolf Spider",
                description = "Active hunter that does not spin webs to catch prey, instead relying on speed and camouflage on the forest floor.",
                temperature_min = 18.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 87,
                name_latin = "Heteropoda venatoria",
                name_common = "Brown Huntsman Spider",
                description = "Renowned for its incredible speed and flattened body, allowing it to squeeze into tight crevices and wall corners.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 75.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 88,
                name_latin = "Dolomedes triton",
                name_common = "Six-Spotted Fishing Spider",
                description = "Capable of running across the surface of water and diving beneath to catch small fish or aquatic insects.",
                temperature_min = 18.0,
                temperature_max = 26.0,
                humidity_min = 70.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 89,
                name_latin = "Araneus diadematus",
                name_common = "Cross Orb-Weaver",
                description = "Spins large, circular geometric webs daily; helps control garden pests and prefers outdoor-like ventilation.",
                temperature_min = 15.0,
                temperature_max = 25.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 90,
                name_latin = "Gorgyrella spp.",
                name_common = "African Trapdoor Spider",
                description = "Lifts a perfectly camouflaged hinged lid to ambush prey; spends almost its entire life in a silk-lined burrow.",
                temperature_min = 20.0,
                temperature_max = 28.0,
                humidity_min = 60.0,
                humidity_max = 75.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 91,
                name_latin = "Macrothele gigas",
                name_common = "Giant Funnel-Web Spider",
                description = "Asian funnel-web species that creates massive sheet webs; very fast and defensive but lacks the lethality of Australian cousins.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 70.0,
                humidity_max = 80.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 92,
                name_latin = "Latrodectus geometricus",
                name_common = "Brown Widow Spider",
                description = "Recognizable by the orange hourglass mark and spiked egg sacs; less aggressive than the Black Widow.",
                temperature_min = 22.0,
                temperature_max = 30.0,
                humidity_min = 40.0,
                humidity_max = 60.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 93,
                name_latin = "Steatoda grossa",
                name_common = "False Black Widow",
                description = "Frequently found in dark, damp areas of buildings; its bite is medically insignificant but resembles a bee sting.",
                temperature_min = 15.0,
                temperature_max = 26.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 94,
                name_latin = "Scytodes thoracica",
                name_common = "Spitting Spider",
                description = "Slow-moving predator that fires a mixture of silk and venom to immobilize prey from a distance.",
                temperature_min = 20.0,
                temperature_max = 28.0,
                humidity_min = 50.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 95,
                name_latin = "Pholcus phalangioides",
                name_common = "Long-bodied Cellar Spider",
                description = "Vibrates rapidly in its web when disturbed to become a blur; known for preying on much larger spiders.",
                temperature_min = 15.0,
                temperature_max = 28.0,
                humidity_min = 40.0,
                humidity_max = 70.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 96,
                name_latin = "Phalangium opilio",
                name_common = "European Harvestman",
                description = "Often mistaken for a spider but lacks silk glands and venom; feeds on a variety of organic matter and small insects.",
                temperature_min = 15.0,
                temperature_max = 26.0,
                humidity_min = 50.0,
                humidity_max = 75.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 97,
                name_latin = "Chelifer cancroides",
                name_common = "House Pseudoscorpion",
                description = "Teeny-tiny 'book scorpion' that hunts carpet beetle larvae and mites; hitches rides on flying insects to travel.",
                temperature_min = 18.0,
                temperature_max = 26.0,
                humidity_min = 70.0,
                humidity_max = 85.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 98,
                name_latin = "Galeodes spp.",
                name_common = "Camel Spider",
                description = "Possesses massive chelicerae for its size; extremely fast desert runner that is neither a spider nor a scorpion.",
                temperature_min = 24.0,
                temperature_max = 35.0,
                humidity_min = 10.0,
                humidity_max = 25.0,
                light_cycle_h = 12
            ),

            Species(
                species_id = 99,
                name_latin = "Charinus spp.",
                name_common = "Dwarf Tailless Whip Scorpion",
                description = "Cryptic and extremely flat; perfect for small bioactive setups where it hunts micro-prey like springtails.",
                temperature_min = 22.0,
                temperature_max = 28.0,
                humidity_min = 75.0,
                humidity_max = 90.0,
                light_cycle_h = 12
            )

        )

        defaultSpecies.forEach { spec ->
            speciesRepository.insertSpecies(
                nameLatin = spec.name_latin,
                nameCommon = spec.name_common,
                description = spec.description,
                temperatureMin = spec.temperature_min,
                temperatureMax = spec.temperature_max,
                humidityMin = spec.humidity_min,
                humidityMax = spec.humidity_max,
                lightCycleH = spec.light_cycle_h
            )
        }
    }
}
