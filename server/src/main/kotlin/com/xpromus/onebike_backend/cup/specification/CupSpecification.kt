package com.xpromus.onebike_backend.cup.specification

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.dto.CupFilter
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object CupSpecification {

    fun withFilter(
        filter: CupFilter
    ): Specification<Cup> {
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

            filter.cupName?.let {
                predicates.add(
                    builder.like(
                        builder.lower(
                            root.get<String>("cupName")
                        ),
                        "%${it.lowercase()}%"
                    )
                )
            }

            builder.and(*predicates.toTypedArray())
        }
    }

}