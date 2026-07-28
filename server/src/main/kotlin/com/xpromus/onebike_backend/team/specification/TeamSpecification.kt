package com.xpromus.onebike_backend.team.specification

import com.xpromus.onebike_backend.team.Team
import com.xpromus.onebike_backend.team.dto.TeamFilter
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object TeamSpecification {

    fun withFilter(
        filter: TeamFilter
    ): Specification<Team> {
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

            filter.teamName?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("teamName")
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            filter.shortName?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("shortName")
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            builder.and(*predicates.toTypedArray())
        }
    }

}
