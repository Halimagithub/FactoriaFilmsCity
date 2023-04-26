package com.factoriafilmscity.factoriafilmscity.controllers

import com.factoriafilmscity.factoriafilmscity.repositories.Movie
import com.factoriafilmscity.factoriafilmscity.repositories.MovieRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MovieController(private val movieRepository: MovieRepository) {

    @GetMapping("/movies")
    fun allMovies(): List<Movie?>? {
        return movieRepository.findAll()
    }

    @GetMapping("/movies/{id}")
    fun findMovie(@PathVariable id: Long): Movie? {
        return movieRepository.findById(id).orElseThrow { MovieNotFoundException() }
    }

    @PostMapping("/movies")
    fun addMovie(@RequestBody movie: Movie): Movie? {
        return movieRepository.save(movie)
    }
    @DeleteMapping("/movies/{id}")
    fun deleteMovieById(@PathVariable id: Long): Movie? {
        val movie: Movie = movieRepository.findById(id).orElseThrow { MovieNotFoundException() }
        movieRepository.deleteById(id)
        return movie
    }

    @PutMapping("/movies")
    fun updateMovieById(@RequestBody movie: Movie): Movie? {
        movie.id?.let { movieRepository.findById(it).orElseThrow { MovieNotFoundException() } }
        return movieRepository.save(movie)
    }
}


    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "movie not found")
    class MovieNotFoundException : RuntimeException()





