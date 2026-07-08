package com.xpromus.onebike_backend.nation

import org.springframework.data.jpa.repository.JpaRepository

interface NationRepository : JpaRepository<Nation, Long> {
}
