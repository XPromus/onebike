package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.rider.Rider
import jakarta.persistence.*

@Entity(name = "nation")
@Table(name = "nations")
class Nation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var longName: String = "",

    @Column(nullable = false, unique = true)
    var shortName: String = "",

    @Column(nullable = false, unique = true)
    var flagEmoji: String = "",

    @OneToMany(
        mappedBy = "nation",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY,
    )
    var riders: MutableList<Rider> = mutableListOf(),

    @OneToMany(
        mappedBy = "nation",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY,
    )
    var cups: MutableList<Cup> = mutableListOf(),

    @OneToMany(
        mappedBy = "nation",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY,
    )
    var races: MutableList<Race> = mutableListOf(),
)
