package com.xpromus.onebike_backend.cup

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CupRepository : JpaRepository<Cup, Long>, JpaSpecificationExecutor<Cup> {

    fun findAllByNationId(
        cupNationId: Long,
        specification: Specification<Cup>,
        pageable: Pageable
    ): Page<Cup>

    @Query("SELECT c.nation.id, c.id FROM cup c WHERE c.nation.id IN :nationIds")
    fun findIdsByNationIds(
        @Param("nationIds") nationIds: Collection<Long>
    ): List<Array<Any>>
}
