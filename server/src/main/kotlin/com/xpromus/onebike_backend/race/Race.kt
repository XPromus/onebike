package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.placement.Placement
import com.xpromus.onebike_backend.nation.Nation
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Race(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var raceName: String = "",
    @Column(nullable = false)
    var lengthInKm: Float = 0.0f,
    @ManyToOne
    var country: Nation = Nation(),
    @ManyToOne
    var cup: Cup? = null,
    @OneToMany
    var placements: MutableList<Placement> = mutableListOf()
)
