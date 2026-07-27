package com.xpromus.onebike_backend.nation.specification

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.dto.NationFilter
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object NationSpecification {

    fun withFilter(
        filter: NationFilter
    ): Specification<Nation> {
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

            filter.longName?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("longName"),
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            filter.shortName?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("shortName"),
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            builder.and(*predicates.toTypedArray())
        }
    }

}