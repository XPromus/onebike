package com.xpromus.onebike_backend.placement

import org.springframework.data.jpa.repository.JpaRepository

interface PlacementRepository : JpaRepository<Placement, Long> {

    fun findPlacementsByRaceId(raceId: Long): MutableList<Placement>

    fun getPlacementsByRiderId(riderId: Long): MutableList<Placement>

}
