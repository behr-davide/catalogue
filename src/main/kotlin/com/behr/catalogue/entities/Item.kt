package com.behr.catalogue.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "item")
@JsonIgnoreProperties(ignoreUnknown = true)
data class Item(
        @Column val name: String,
        @Column val description: String,
        @Column val price: BigDecimal,
        @Column val itemTypeId: Int? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Int? = null
}