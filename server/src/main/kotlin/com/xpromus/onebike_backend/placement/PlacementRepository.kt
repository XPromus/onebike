package com.xpromus.onebike_backend.placement

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PlacementRepository : JpaRepository<Placement, Long>, JpaSpecificationExecutor<Placement> {

    @Query("SELECT p.rider.id, p.id FROM placement p WHERE p.rider.id IN :riderIds")
    fun findIdsByRiderIds(
        @Param("riderIds") riderIds: Collection<Long>
    ): List<Array<Any>>

    @Query("SELECT p.rider.id, p FROM placement p WHERE p.rider.id IN :riderIds")
    fun findByRiderIds(
        @Param("riderIds") riderIds: Collection<Long>
    ): List<Array<Any>>

    @Query("SELECT p.race.id, p.id FROM placement p WHERE p.race.id IN :raceIds")
    fun findIdsByRaceIds(
        @Param("raceIds") raceIds: Collection<Long>
    ): List<Array<Any>>

    @Query("SELECT p.race.id, p FROM placement p WHERE p.race.id IN :raceIds")
    fun findByRaceIds(
        @Param("raceIds") raceIds: Collection<Long>
    ): List<Array<Any>>

}
