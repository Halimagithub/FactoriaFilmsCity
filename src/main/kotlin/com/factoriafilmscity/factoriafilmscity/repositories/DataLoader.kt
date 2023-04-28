package com.factoriafilmscity.factoriafilmscity.repositories

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(private val movieRepository: MovieRepository) {
    @PostConstruct
    fun load() {
        val movies = listOf(
            Movie("Megalodón2: La Trinchera", "https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0", "Ben Wheatley", "Secuela de 'The Meg' (2018).", 2023, "Ciencia ficción, acción, terror, suspense"),
            Movie("Stab Who Scream", "https://tse3.mm.bing.net/th?id=OIP.bjG2hBPKMjso0Mu9KneuCAHaLP&pid=Api&P=0", "Roberto Rodríguez", "Stab Who Scream", 2023, "Terror y misterio"),
            Movie("OPPENHEIMER", "https://tse1.mm.bing.net/th?id=OIP.n_wf-b7Pp4h-gP3a9meQAAHaLu&pid=Api&P=0", "Christopher Nolan", "Christopher Nolan se encarga de dirigir este filme centrado en la figura de J. Robert Oppenheimer, el hombre que desarrolló la bomba atómica.",2023, "Drama e historia")
        )


        movieRepository.saveAll(movies)
        println("Cargamos datos de prueba cuando arrancamos el servidor: $movies")
    }
}