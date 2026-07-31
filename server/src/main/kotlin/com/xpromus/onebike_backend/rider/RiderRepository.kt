package com.xpromus.onebike_backend.rider

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RiderRepository : JpaRepository<Rider, Long>, JpaSpecificationExecutor<Rider> {

    @Query("SELECT r.nation.id, r.id FROM rider r WHERE r.nation.id IN :nationIds")
    fun findIdsByNationIds(
        @Param("nationIds") nationIds: Collection<Long>
    ): List<Array<Any>>

    @Query("SELECT r.id FROM rider r WHERE r.team.id = :teamId")
    fun findIdsByTeamId(
        @Param("teamId") teamId: Long
    ): List<Long>

    @Query("SELECT r.team.id, r.id FROM rider r WHERE r.team.id IN :teamIds")
    fun findIdsByTeamIds(
        @Param("teamIds") teamIds: Collection<Long>
    ): List<Array<Any>>

    @Query("SELECT r.team.id, r FROM rider r WHERE r.team.id IN :teamIds")
    fun findByTeamIds(
        @Param("teamIds") teamIds: Collection<Long>
    ): List<Array<Any>>

}
