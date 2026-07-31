package com.xpromus.onebike_backend.placement.specification

import com.xpromus.onebike_backend.placement.Placement
import com.xpromus.onebike_backend.placement.dto.PlacementFilter
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.rider.Rider
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object PlacementSpecification {

    fun withFilter(
        filter: PlacementFilter,
        raceId: Long? = null,
        riderId: Long? = null,
    ): Specification<Placement> {
        return Specification { root, _, builder ->
            val predicates = mutableListOf<Predicate>()

            filter.id?.let {
                predicates.add(
                    builder.equal(
                        root.get<Long>("id"),
                        it
                    )
                )
            }

            filter.points?.let {
                predicates.add(
                    builder.equal(
                        root.get<Int>("points"),
                        it
                    )
                )
            }

            filter.minPoints?.let {
                predicates.add(
                    builder.greaterThanOrEqualTo(
                        root.get<Int>("points"),
                        it
                    )
                )
            }

            filter.maxPoints?.let {
                predicates.add(
                    builder.lessThanOrEqualTo(
                        root.get<Int>("points"),
                        it
                    )
                )
            }

            filter.finishTimeInSeconds?.let {
                predicates.add(
                    builder.equal(
                        root.get<Int>("finishTimeInSeconds"),
                        it
                    )
                )
            }

            filter.minFinishTimeInSeconds?.let {
                predicates.add(
                    builder.greaterThanOrEqualTo(
                        root.get<Int>("finishTimeInSeconds"),
                        it
                    )
                )
            }

            filter.maxFinishTimeInSeconds?.let {
                predicates.add(
                    builder.lessThanOrEqualTo(
                        root.get<Int>("finishTimeInSeconds"),
                        it
                    )
                )
            }

            filter.finishStatus?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("finishStatus")
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            raceId?.let {
                predicates.add(
                    builder.equal(
                        root.get<Race>("race").get<Long>("id"),
                        it
                    )
                )
            }

            riderId?.let {
                predicates.add(
                    builder.equal(
                        root.get<Rider>("rider").get<Long>("id"),
                        it
                    )
                )
            }

            builder.and(*predicates.toTypedArray())
        }
    }

}
