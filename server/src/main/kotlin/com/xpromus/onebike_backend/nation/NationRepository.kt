package com.xpromus.onebike_backend.nation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface NationRepository : JpaRepository<Nation, Long>, JpaSpecificationExecutor<Nation>
