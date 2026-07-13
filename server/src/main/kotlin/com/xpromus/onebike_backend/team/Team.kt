package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.rider.Rider
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

@Entity(name = "team")
@Table(name = "teams")
class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var teamName: String = "",

    @Column(nullable = false)
    var shortName: String = "",

    @Column(nullable = false)
    var teamDescription: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nation_id")
    var nationality: Nation = Nation(),

    @OneToMany(
        mappedBy = "team",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var riders: MutableList<Rider> = mutableListOf(),
)
