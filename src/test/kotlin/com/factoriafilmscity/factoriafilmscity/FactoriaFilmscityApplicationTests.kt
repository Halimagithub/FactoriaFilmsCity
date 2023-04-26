package com.factoriafilmscity.factoriafilmscity

import com.factoriafilmscity.factoriafilmscity.repositories.Movie
import com.factoriafilmscity.factoriafilmscity.repositories.MovieRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status






@SpringBootTest(
    classes = arrayOf(FactoriafilmscityApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FactoriafilmscityApplicationTests(@Autowired val mockMvc: MockMvc) {

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @AfterEach
    fun tearDown() {
        movieRepository.deleteAll()
    }

    @Test
    @Throws(Exception::class)
    fun `returns the existing movies`() {
        addTestMovies()
        mockMvc.perform(get("/movies"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[*]", hasSize<Int>(6)))
            .andExpect(jsonPath("$[0].title", equalTo("Megalodón2: La Trinchera")))
            .andExpect(jsonPath("$[0].coverImage", equalTo("https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0")))
            .andExpect(jsonPath("$[0].director", equalTo("Ben Wheatley")))
            .andExpect(jsonPath("$[0].synopsis", equalTo("Secuela de 'The Meg' (2018).")))
            .andExpect(jsonPath("$[0].releaseYear", equalTo(2023)))
            .andExpect(jsonPath("$[1].title", equalTo("Stab Who Scream")))
            .andExpect(jsonPath("$[1].coverImage", equalTo("https://tse3.mm.bing.net/th?id=OIP.bjG2hBPKMjso0Mu9KneuCAHaLP&pid=Api&P=0")))
            .andExpect(jsonPath("$[1].director", equalTo("Roberto Rodríguez")))
            .andExpect(jsonPath("$[1].synopsis", equalTo("Stab Who Scream")))
            .andExpect(jsonPath("$[1].releaseYear", equalTo(2023)))
            .andExpect(jsonPath("$[2].title", equalTo("OPPENHEIMER")))
            .andExpect(jsonPath("$[2].coverImage", equalTo("https://tse1.mm.bing.net/th?id=OIP.n_wf-b7Pp4h-gP3a9meQAAHaLu&pid=Api&P=0")))
            .andExpect(jsonPath("$[2].director", equalTo("Christopher Nolan")))
            .andExpect(jsonPath("$[2].synopsis", equalTo("Christopher Nolan se encarga de dirigir este filme centrado en la figura de J. Robert Oppenheimer, el hombre que desarrolló la bomba atómica.")))
            .andExpect(jsonPath("$[2].releaseYear", equalTo(2023)))
            .andDo(print())
    }

    @Test
    @Throws(Exception::class)
    fun `allows to create a new movie`() {
        mockMvc.perform(
            post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Megalodón2: La Trinchera\", \"coverImage\": \"https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0\", \"director\": \"Ben Wheatley\", \"synopsis\": \"Secuela de 'The Meg' (2018).\", \"releaseYear\": 2023}")
        ).andExpect(status().isOk)
        val movies: List<Movie> = movieRepository.findAll()
        assertThat(
            movies, contains(
                 allOf(
                    hasProperty("title", `is`("Megalodón2: La Trinchera")),
                    hasProperty("CoverImage", `is`("https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0")),
                    hasProperty("director", `is`("Ben Wheatley")),
                    hasProperty("synopsis", `is`("Secuela de 'The Meg' (2018).")),
                    hasProperty("releaseYear", `is`(2023))
                    )
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun `allows to find a movie by id`() {
        val movie: Movie = movieRepository.save(Movie("Megalodón2: La Trinchera", "https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0", "Ben Wheatley", "Secuela de 'The Meg' (2018).", 2023))
        mockMvc.perform(get("/movies/" + movie.id))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title", equalTo("Megalodón2: La Trinchera")))
            .andExpect(jsonPath("$.coverImage", equalTo("https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0")))
            .andExpect(jsonPath("$.director", equalTo("Ben Wheatley")))
            .andExpect(jsonPath("$.synopsis", equalTo("Secuela de 'The Meg' (2018).")))
            .andExpect(jsonPath("$.releaseYear", equalTo(2023)))
    }

    @Test
    @Throws(Exception::class)
    fun `returns an error if trying to get a movie that does not exist`() {
        mockMvc.perform(get("/movies/"))
            .andExpect(status().isNotFound())
    }

    private fun addTestMovies() {
        val movies: List<Movie> = listOf(
            Movie(
                "Megalodón2: La Trinchera",
                "https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0",
                "Ben Wheatley",
                "Secuela de 'The Meg' (2018).",
                2023
            ),
            Movie(
                "Stab Who Scream",
                "https://tse3.mm.bing.net/th?id=OIP.bjG2hBPKMjso0Mu9KneuCAHaLP&pid=Api&P=0",
                "Roberto Rodríguez",
                "Stab Who Scream",
                2023
            ),
            Movie(
                "OPPENHEIMER",
                "https://tse1.mm.bing.net/th?id=OIP.n_wf-b7Pp4h-gP3a9meQAAHaLu&pid=Api&P=0",
                "Christopher Nolan",
                "Christopher Nolan se encarga de dirigir este filme centrado en la figura de J. Robert Oppenheimer, el hombre que desarrolló la bomba atómica.",
                2023
            )
        )
        movies.forEach(movieRepository::save)
    }
    @Test
    @Throws(Exception::class)
    fun `allows to delete a movie by id`() {
        val movie: Movie = movieRepository.save(Movie("Megalodón2: La Trinchera", "https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0", "Ben Wheatley", "Secuela de 'The Meg' (2018).", 2023))
        mockMvc.perform(delete("/movies/" + movie.id))
            .andExpect(status().isOk)
        val movies: List<Movie> = movieRepository.findAll()
        assertThat(
            movies, not(
                contains(
                    allOf(
                        hasProperty("title", `is`("Megalodón2: La Trinchera")),
                        hasProperty("CoverImage", `is`("https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0")),
                        hasProperty("director", `is`("Ben Wheatley")),
                        hasProperty("synopsis", `is`("Secuela de 'The Meg' (2018).")),
                        hasProperty("releaseYear", `is`(2023))
                    )
                )
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun `returns an error if trying to delete a coder that does not exist`() {
        mockMvc.perform(delete("/coders/1"))
            .andExpect(status().isNotFound())
    }
    @Test
    @Throws(Exception::class)
    fun `allows to modify a movie`() {
        val movie: Movie = movieRepository.save(Movie("Megalodón2: La Trinchera", "https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0", "Ben Wheatley", "Secuela de 'The Meg' (2018).", 2023))
        mockMvc.perform(
            put("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"" + movie.id + "\", \"title\": \"Megalodón2: La Trinchera\", \"coverImage\": \"https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0\", \"director\": \"Ben Wheatley\", \"synopsis\": \"Secuela de 'The Meg' (2018).\", \"releaseYear\": 2023}")
        ).andExpect(status().isOk)
        val movies: List<Movie> = movieRepository.findAll()
        assertThat(movies, hasSize(4))
        assertThat(movies[0].title, equalTo("Megalodón2: La Trinchera"))
        assertThat(movies[0].coverImage, equalTo("https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0"))
        assertThat(movies[0].director, equalTo("Ben Wheatley"))
        assertThat(movies[0].synopsis, equalTo("Secuela de 'The Meg' (2018)."))
        assertThat(movies[0].releaseYear, equalTo(2023))

    }

    @Test
    @Throws(Exception::class)
    fun `returns an error when trying to modify a movie that does not exist`() {
        addTestMovies()
        mockMvc.perform(
            put("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"" + -1 + "\", \"title\": \"Megalodón2: La Trinchera\", \"coverImage\": \"https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0\", \"director\": \"Ben Wheatley\", \"synopsis\": \"Secuela de 'The Meg' (2018).\", \"releaseYear\": 2023}")
        ).andExpect(status().isNotFound())
    }
}
