package com.xpromus.onebike_backend.cup

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface CupRepository : JpaRepository<Cup, Long> {
    fun findAllByNationId(cupNationId: Long, sort: Sort?): MutableList<Cup>
    fun findAllByCupNameLike(cupName: String, sort: Sort?): MutableList<Cup>
    fun findCupsByCupNameLike(cupName: String, sort: Sort?): MutableList<Cup>
}
