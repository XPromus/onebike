package com.xpromus.onebike_backend.cup.placement

import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.rider.Rider
import jakarta.persistence.*

@Entity
@Table
class Placement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    var points: Int = 0,
    @ManyToOne
    var rider: Rider = Rider(),
    @ManyToOne
    var race: Race = Race(),
)
