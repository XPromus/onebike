package com.xpromus.onebike_backend.cup

import org.springframework.data.jpa.repository.JpaRepository

interface CupRepository : JpaRepository<Cup, Long> {
    fun findAllByCupNationId(cupNationId: Long): MutableList<Cup>
    fun findAllByCupNameLike(cupName: String): MutableList<Cup>
    fun findCupsByCupNameLike(cupName: String): MutableList<Cup>
}
