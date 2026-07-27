package com.xpromus.onebike_backend.cup

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CupRepository : JpaRepository<Cup, Long> {
    fun findAllByNationId(cupNationId: Long, sort: Sort?): MutableList<Cup>
    fun findAllByCupNameLike(cupName: String, sort: Sort?): MutableList<Cup>
    fun findCupsByCupNameLike(cupName: String, sort: Sort?): MutableList<Cup>

    @Query("SELECT c.nation.id, c.id FROM cup c WHERE c.nation.id IN :nationIds")
    fun findIdsByNationIds(
        @Param("nationIds") nationIds: Collection<Long>
    ): List<Array<Any>>
}
