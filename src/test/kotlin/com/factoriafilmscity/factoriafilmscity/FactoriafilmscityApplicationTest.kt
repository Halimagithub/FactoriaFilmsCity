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
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.equalTo("Megalodón2: La trinchera")))
            //.andExpect(MockMvcResultMatchers.jsonPath("$[0].favouriteLanguage", Matchers.equalTo("Java")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.equalTo("Stab Who Scream")))
            //.andExpect(MockMvcResultMatchers.jsonPath("$[1].favouriteLanguage", Matchers.equalTo("Kotlin")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].title", Matchers.equalTo("OPPENHEIMER")))
            //.andExpect(MockMvcResultMatchers.jsonPath("$[2].favouriteLanguage", Matchers.equalTo("Python")))
            .andDo(MockMvcResultHandlers.print())
    }
}