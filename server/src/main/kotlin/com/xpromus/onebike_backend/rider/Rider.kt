package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.cup.placement.CupPlacement
import com.xpromus.onebike_backend.nation.Nation
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table
class Rider(
    @Id
    var id: Long? = null,
    @Column(nullable = false)
    var firstName: String = "",
    @Column(nullable = false)
    var lastName: String = "",
    @OneToMany
    var cupPlacements: MutableList<CupPlacement> = mutableListOf(),
    @ManyToOne
    var nation: Nation = Nation(),
)
