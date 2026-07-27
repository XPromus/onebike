package com.xpromus.onebike_backend.rider

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RiderRepository : JpaRepository<Rider, Long> {

    fun findRidersByFirstNameLikeOrLastNameLike(
        firstName: String,
        lastName: String,
        sort: Sort
    ): MutableList<Rider>

    @Query("SELECT r.nation.id, r.id FROM rider r WHERE r.nation.id IN :nationIds")
    fun findIdsByNationIds(
        @Param("nationIds") nationIds: Collection<Long>
    ): List<Array<Any>>

}
