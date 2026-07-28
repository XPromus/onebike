package com.xpromus.onebike_backend.race.specification

import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.dto.RaceFilter
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object RaceSpecification {

    fun withFilter(
        filter: RaceFilter
    ): Specification<Race> {
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

            filter.raceName?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("raceName")
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            filter.lengthInKm?.let {
                predicates.add(
                    builder.equal(
                        root.get<Float>("lengthInKm"),
                        it
                    )
                )
            }

            filter.minLengthInKm?.let {
                predicates.add(
                    builder.greaterThanOrEqualTo(
                        root.get<Float>("lengthInKm"),
                        it
                    )
                )
            }

            filter.maxLengthInKm?.let {
                predicates.add(
                    builder.lessThanOrEqualTo(
                        root.get<Float>("lengthInKm"),
                        it
                    )
                )
            }

            filter.raceDate?.let {
                predicates.add(
                    builder.equal(
                        root.get<java.time.LocalDate>("raceDate"),
                        it
                    )
                )
            }

            filter.minRaceDate?.let {
                predicates.add(
                    builder.greaterThanOrEqualTo(
                        root.get<java.time.LocalDate>("raceDate"),
                        it
                    )
                )
            }

            filter.maxRaceDate?.let {
                predicates.add(
                    builder.lessThanOrEqualTo(
                        root.get<java.time.LocalDate>("raceDate"),
                        it
                    )
                )
            }

            filter.startTime?.let {
                predicates.add(
                    builder.equal(
                        root.get<java.time.Instant>("startTime"),
                        it
                    )
                )
            }

            filter.minStartTime?.let {
                predicates.add(
                    builder.greaterThanOrEqualTo(
                        root.get<java.time.Instant>("startTime"),
                        it
                    )
                )
            }

            filter.maxStartTime?.let {
                predicates.add(
                    builder.lessThanOrEqualTo(
                        root.get<java.time.Instant>("startTime"),
                        it
                    )
                )
            }

            builder.and(*predicates.toTypedArray())
        }
    }

}
