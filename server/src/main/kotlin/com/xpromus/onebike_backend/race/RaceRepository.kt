package com.xpromus.onebike_backend.race

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RaceRepository : JpaRepository<Race, Long>, JpaSpecificationExecutor<Race> {

    @Query("SELECT ra.nation.id, ra.id FROM race ra WHERE ra.nation.id IN :nationIds")
    fun findIdsByNationIds(
        @Param("nationIds") nationIds: Collection<Long>
    ): List<Array<Any>>

    @Query("SELECT ra.cup.id, ra.id FROM race ra WHERE ra.cup.id IN :cupIds")
    fun findIdsByCupIds(
        @Param("cupIds") cupIds: Collection<Long>
    ): List<Array<Any>>

    @Query("SELECT ra.id FROM race ra WHERE ra.cup.id = :cupId")
    fun findIdsByCupId(
        @Param("cupId") cupId: Long
    ): List<Long>

    @Query("SELECT ra.cup.id, ra FROM race ra WHERE ra.cup.id IN :cupIds")
    fun findByCupIds(
        @Param("cupIds") cupIds: Collection<Long>
    ): List<Array<Any>>

}
