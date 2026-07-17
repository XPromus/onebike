package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.placement.Placement
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.team.Team
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
import java.time.LocalDate

@Entity(name = "rider")
@Table(name = "riders")
class Rider(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var firstName: String = "",

    @Column(nullable = false)
    var lastName: String = "",

    @Column(nullable = false)
    var dateOfBirth: LocalDate = LocalDate.now(),

    @OneToMany(
        mappedBy = "rider",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var placements: MutableList<Placement> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nation_id")
    var nation: Nation = Nation(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    var team: Team? = null,
)
