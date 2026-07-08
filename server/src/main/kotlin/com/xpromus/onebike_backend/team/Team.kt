package com.xpromus.onebike_backend.team

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
class Team(
    @Id
    var id: Long? = null
)
