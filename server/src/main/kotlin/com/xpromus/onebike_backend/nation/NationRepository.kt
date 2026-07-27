package com.xpromus.onebike_backend.nation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface NationRepository : JpaRepository<Nation, Long>, JpaSpecificationExecutor<Nation>
