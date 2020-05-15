package com.behr.catalogue.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "item_type")
@JsonIgnoreProperties(ignoreUnknown = true)
data class Category(
        @Column val name: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Int? = null
}