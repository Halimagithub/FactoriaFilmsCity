package com.factoriafilmscity.factoriafilmscity

import com.factoriafilmscity.factoriafilmscity.repositories.MovieRepository
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@Test
fun addTestMovies() {
}


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
        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$[*]", Matchers.hasSize<Int>(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.equalTo("Megalodón2: La Trinchera")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].coverImage", Matchers.equalTo("https://tse2.mm.bing.net/th?id=OIP.y749OBQqVZxIPEtYcXBxIQHaLH&pid=Api&P=0")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].director", Matchers.equalTo("Ben Wheatley")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].synopsis", Matchers.equalTo("Secuela de 'The Meg' (2018).")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseYear", Matchers.equalTo(2023)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.equalTo("Stab Who Scream")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].coverImage", Matchers.equalTo("https://tse3.mm.bing.net/th?id=OIP.bjG2hBPKMjso0Mu9KneuCAHaLP&pid=Api&P=0")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].director", Matchers.equalTo("Roberto Rodríguez")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].synopsis", Matchers.equalTo("Stab Who Scream")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].releaseYear", Matchers.equalTo(2023)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].title", Matchers.equalTo("OPPENHEIMER")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].coverImage", Matchers.equalTo("https://tse1.mm.bing.net/th?id=OIP.n_wf-b7Pp4h-gP3a9meQAAHaLu&pid=Api&P=0")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].director", Matchers.equalTo("Christopher Nolan")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].synopsis", Matchers.equalTo("Christopher Nolan se encarga de dirigir este filme centrado en la figura de J. Robert Oppenheimer, el hombre que desarrolló la bomba atómica.")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].releaseYear", Matchers.equalTo(2023)))
            .andDo(MockMvcResultHandlers.print())
    }
}