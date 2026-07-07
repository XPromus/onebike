package com.xpromus.onebike_backend.rider

import org.springframework.data.jpa.repository.JpaRepository

interface RiderRepository : JpaRepository<Rider, Long> {
}
