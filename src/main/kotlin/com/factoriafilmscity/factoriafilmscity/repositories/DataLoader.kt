package com.factoriafilmscity.factoriafilmscity.repositories

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(private val movieRepository: MovieRepository) {
    @PostConstruct
    fun load() {
        val movies = listOf(
                Movie("Megalodón2: La trinchera", "src=url(https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0"),
                Movie("Stab Who Scream", "https://tse3.mm.bing.net/th?id=OIP.bjG2hBPKMjso0Mu9KneuCAHaLP&pid=Api&P=0"),
                Movie("OPPENHEIMER", "https://tse1.mm.bing.net/th?id=OIP.n_wf-b7Pp4h-gP3a9meQAAHaLu&pid=Api&P=0")
        )

        movieRepository.saveAll(movies)
        println("Cargamos datos de prueba cuando arrancamos el servidor: $movies")
    }
}