package com.xpromus.onebike_backend.race

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RaceRepository : JpaRepository<Race, Long> {

    fun findRacesByRaceNameLike(
        raceName: String,
        sort: Sort
    ): MutableList<Race>

    @Query("SELECT ra.nation.id, ra.id FROM race ra WHERE ra.nation.id IN :nationIds")
    fun findIdsByNationIds(
        @Param("nationIds") nationIds: Collection<Long>
    ): List<Array<Any>>

}
