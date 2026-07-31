package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.cup.CupRepository
import com.xpromus.onebike_backend.nation.dto.*
import com.xpromus.onebike_backend.nation.mapper.toEntity
import com.xpromus.onebike_backend.nation.mapper.toGetDto
import com.xpromus.onebike_backend.nation.mapper.toGetWithChildrenDto
import com.xpromus.onebike_backend.nation.mapper.toNewEntity
import com.xpromus.onebike_backend.nation.specification.NationSpecification
import com.xpromus.onebike_backend.race.RaceRepository
import com.xpromus.onebike_backend.rider.RiderRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NationService(
    private val nationRepository: NationRepository,
    private val riderRepository: RiderRepository,
    private val cupRepository: CupRepository,
    private val raceRepository: RaceRepository,
) {
    @Transactional(readOnly = true)
    fun findNations(
        filter: NationFilter,
        pageable: Pageable
    ): Page<GetNationDto> {
        val spec = NationSpecification.withFilter(filter)
        return nationRepository.findAll(spec, pageable).map {
            it.toGetDto()
        }
    }

    @Transactional(readOnly = true)
    fun findNationsWithChildren(
        filter: NationFilter,
        pageable: Pageable
    ): Page<GetNationWithChildrenDto> {
        val spec = NationSpecification.withFilter(filter)
        val nations = nationRepository.findAll(spec, pageable)
        val nationIds = nations.content.map { it.id!! }

        val riderIds = riderRepository.findIdsByNationIds(nationIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { it[1] as Long }
            )
        val cupIds = cupRepository.findIdsByNationIds(nationIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { it[1] as Long }
            )
        val raceIds = raceRepository.findIdsByNationIds(nationIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { it[1] as Long }
            )

        return nations.map { nation ->
            nation.toGetWithChildrenDto(
                riderIds = riderIds[nation.id] ?: emptyList(),
                cupIds = cupIds[nation.id] ?: emptyList(),
                raceIds = raceIds[nation.id] ?: emptyList(),
            )
        }
    }

    @Transactional
    fun putNation(
        id: Long,
        putNationDto: PutNationDto
    ): Pair<GetNationDto, Boolean> {
        val existingNation = nationRepository.findByIdOrNull(id)
        val nationToSave: Nation = existingNation?.let {
            putNationDto.toEntity(
                original = it
            )
        } ?: run {
            putNationDto.toNewEntity()
        }

        val savedNation = nationRepository.save(nationToSave)
        return savedNation.toGetDto() to (existingNation == null)
    }

    @Transactional
    fun createNation(
        postNationDto: PostNationDto
    ): GetNationDto {
        val newNation = postNationDto.toNewEntity()
        return nationRepository.save(newNation).toGetDto()
    }

    @Transactional
    fun deleteNation(
        id: Long
    ) {
        nationRepository.deleteById(id)
    }
}
