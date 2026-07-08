package com.xpromus.onebike_backend.cup.placement

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.rider.Rider
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table
class CupPlacement(
    @Id
    var id: Long? = null,
    @ManyToOne
    var cup: Cup = Cup(),
    @Column
    var points: Int = 0,
    @ManyToOne
    var rider: Rider = Rider(),
)
