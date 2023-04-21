package com.factoriafilmscity.factoriafilmscity.repositories

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(private val movieRepository: MovieRepository) {
    @PostConstruct
    fun load() {
        val movies = listOf(
                Movie("Megalodón2: La trinchera"),
                Movie("Stab Who Scream"),
                Movie("OPPENHEIMER")
        )

        movieRepository.saveAll(movies)
        println("Cargamos datos de prueba cuando arrancamos el servidor: $movies")
    }
}