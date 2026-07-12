package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.rider.Rider
import jakarta.persistence.*

@Entity
class Nation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    var longName: String = "",
    @Column
    var shortName: String = "",
    @Column
    var flagEmoji: String = "",
    @OneToMany
    var riders: MutableList<Rider> = mutableListOf(),
    @OneToMany
    var cups: MutableList<Cup> = mutableListOf(),
    @OneToMany
    var races: MutableList<Race> = mutableListOf(),
)