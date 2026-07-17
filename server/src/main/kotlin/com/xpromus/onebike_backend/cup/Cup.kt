package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.race.Race
import jakarta.persistence.*

@Entity(name = "cup")
@Table(name = "cups")
class Cup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var cupName: String = "",

    @Column(nullable = true)
    var url: String? = "",

    @OneToMany(
        mappedBy = "cup",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var races: MutableList<Race> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nation_id", nullable = false)
    var nation: Nation = Nation(),
)
