package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.placement.Placement
import com.xpromus.onebike_backend.nation.Nation
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.cglib.core.Local
import java.time.Instant
import java.time.LocalDate

@Entity(name = "race")
@Table(name = "races")
class Race(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var raceName: String = "",

    @Column(nullable = false)
    var lengthInKm: Float = 0.0f,

    @Column(nullable = false)
    var raceDate: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    var startTime: Instant = Instant.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nation_id")
    var nation: Nation = Nation(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cup_id")
    var cup: Cup? = null,

    @OneToMany(
        mappedBy = "race",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var placements: MutableList<Placement> = mutableListOf()
)
