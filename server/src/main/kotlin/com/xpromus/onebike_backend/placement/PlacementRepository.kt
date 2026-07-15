package com.xpromus.onebike_backend.placement

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface PlacementRepository : JpaRepository<Placement, Long> {

    fun findPlacementsByRaceId(
        raceId: Long,
        sort: Sort?
    ): MutableList<Placement>

    fun getPlacementsByRiderId(
        riderId: Long,
        sort: Sort?
    ): MutableList<Placement>

}
