package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.cup.placement.Placement
import com.xpromus.onebike_backend.nation.Nation
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table
class Rider(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var firstName: String = "",
    @Column(nullable = false)
    var lastName: String = "",
    @OneToMany
    var placements: MutableList<Placement> = mutableListOf(),
    @ManyToOne
    var nation: Nation = Nation(),
)
