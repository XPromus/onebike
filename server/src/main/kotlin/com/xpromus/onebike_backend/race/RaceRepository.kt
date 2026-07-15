package com.xpromus.onebike_backend.race

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface RaceRepository : JpaRepository<Race, Long> {

    fun findRacesByRaceNameLike(
        raceName: String,
        sort: Sort
    ): MutableList<Race>

}
