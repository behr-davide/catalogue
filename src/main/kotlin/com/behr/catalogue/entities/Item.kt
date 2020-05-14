package com.behr.catalogue.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "item")
@JsonIgnoreProperties(ignoreUnknown = true)
data class Item(
        @Id @Column val id: Int? = null,
        @Column val name: String? = null,
        @Column val description: String? = null,
        @Column val price: BigDecimal? = null,
        @Column val itemTypeId: Int? = null
)