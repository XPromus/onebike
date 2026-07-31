package com.xpromus.onebike_backend.rider.specification

import com.xpromus.onebike_backend.rider.Rider
import com.xpromus.onebike_backend.rider.dto.RiderFilter
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object RiderSpecification {

    fun withFilter(
        filter: RiderFilter
    ): Specification<Rider> {
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

            filter.firstName?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("firstName")
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            filter.lastName?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("lastName")
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            filter.dateOfBirth?.let {
                predicates.add(
                    builder.equal(
                        root.get<java.time.LocalDate>("dateOfBirth"),
                        it
                    )
                )
            }

            filter.minDateOfBirth?.let {
                predicates.add(
                    builder.greaterThanOrEqualTo(
                        root.get<java.time.LocalDate>("dateOfBirth"),
                        it
                    )
                )
            }

            filter.maxDateOfBirth?.let {
                predicates.add(
                    builder.lessThanOrEqualTo(
                        root.get<java.time.LocalDate>("dateOfBirth"),
                        it
                    )
                )
            }

            builder.and(*predicates.toTypedArray())
        }
    }

}
