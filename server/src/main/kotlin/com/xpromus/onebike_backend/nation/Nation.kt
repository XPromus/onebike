package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.rider.Rider
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany

@Entity
class Nation(
    @Id
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
    var cupPrimaryNations: MutableList<Cup> = mutableListOf(),
    @ManyToMany
    var cupSecondaryNations: MutableList<Cup> = mutableListOf(),
)