package com.xpromus.onebike_backend.rider

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface RiderRepository : JpaRepository<Rider, Long> {

    fun findRidersByFirstNameLikeOrLastNameLike(
        firstName: String,
        lastName: String,
        sort: Sort
    ): MutableList<Rider>

}
