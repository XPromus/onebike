package com.xpromus.onebike_backend.cup.placement

import org.springframework.data.jpa.repository.JpaRepository

interface CupPlacementRepository : JpaRepository<CupPlacement, Long> {

    fun getCupPlacementsByCup_Id(cupId: Long): kotlin.collections.MutableList<com.xpromus.onebike_backend.cup.placement.CupPlacement>

}
