package com.xpromus.onebike_backend.cup

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
class Cup(
    @Id
    var id: Long? = null,
    @Column(nullable = false)
    var cupName: String = "",
)
