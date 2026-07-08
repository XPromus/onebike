package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.placement.CupPlacement
import com.xpromus.onebike_backend.nation.Nation
import jakarta.persistence.*

@Entity
@Table
class Cup(
    @Id
    var id: Long? = null,
    @Column(nullable = false)
    var cupName: String = "",
    @OneToMany
    var placements: MutableList<CupPlacement> = mutableListOf(),
    @ManyToOne
    var primaryNation: Nation = Nation(),
    @ManyToMany
    var secondaryNations: MutableList<Nation> = mutableListOf(),
)
