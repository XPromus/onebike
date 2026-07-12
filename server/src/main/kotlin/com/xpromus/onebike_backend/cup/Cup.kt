package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.race.Race
import jakarta.persistence.*

@Entity
@Table
class Cup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var cupName: String = "",
    @OneToMany
    var races: MutableList<Race> = mutableListOf(),
    @ManyToOne
    var cupNation: Nation = Nation(),
)
