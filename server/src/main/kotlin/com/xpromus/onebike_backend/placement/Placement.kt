package com.xpromus.onebike_backend.placement

import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.rider.Rider
import jakarta.persistence.*

@Entity
@Table(name = "placements")
class Placement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var points: Int = 0,

    @Column
    var finishTimeInSeconds: Int = 0,

    @Column
    var finishStatus: String = "finished",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    var rider: Rider = Rider(),

    @ManyToOne
    @JoinColumn(name = "race_id")
    var race: Race = Race(),
)
