package com.factoriafilmscity.factoriafilmscity.repositories

import jakarta.persistence.*


@Table(name = "movies")
@Entity
data class Movie(
        var title: String,
        var coverImage: String,
        var director: String,
        var synopsis: String,
        var releaseYear: Int,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null
)