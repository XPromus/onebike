package com.xpromus.onebike_backend.team

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.rider.Rider
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table
class Team(
    @Id
    var id: Long? = null,
    var teamName: String,
    var shortName: String,
    var teamDescription: String,
    @ManyToOne
    var nationality: Nation = Nation(),
    @OneToMany
    var riders: MutableList<Rider> = mutableListOf(),
)
