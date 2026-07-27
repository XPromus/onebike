package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.*
import com.xpromus.onebike_backend.cup.mapper.toEntity
import com.xpromus.onebike_backend.cup.mapper.toGetCupDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.mapper.toNewEntity
import com.xpromus.onebike_backend.cup.specification.CupSpecification
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.nation.mapper.toNationDescriptorDto
import com.xpromus.onebike_backend.race.Race
import com.xpromus.onebike_backend.race.RaceRepository
import com.xpromus.onebike_backend.race.mapper.toRaceDescriptorDto
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CupService(
    private val cupRepository: CupRepository,
    private val nationRepository: NationRepository,
    private val raceRepository: RaceRepository,
) {

    @Transactional(readOnly = true)
    fun findCups(
        filter: CupFilter,
        pageable: PageRequest
    ): Page<GetCupDto> {
        val spec = CupSpecification.withFilter(filter)
        val cups = cupRepository.findAll(spec, pageable)
        val cupIds = cups.content.map { it.id!! }

        val raceIds: Map<Long, List<Long>> = raceRepository
            .findIdsByCupIds(cupIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { it[1] as Long }
            )

        return cups.map { cup ->
            cup.toGetCupDto(
                raceIds = raceIds[cup.id] ?: emptyList(),
                nationId = cup.nation.id!!
            )
        }
    }

    @Transactional(readOnly = true)
    fun findCupsWithChildren(
        filter: CupFilter,
        pageable: PageRequest
    ): Page<GetCupWithChildrenDto> {
        val spec = CupSpecification.withFilter(filter)
        val cups = cupRepository.findAll(spec, pageable)
        val cupIds = cups.content.map { it.id!! }

        val raceDescriptors = raceRepository
            .findByCupIds(cupIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { (it[1] as Race).toRaceDescriptorDto() }
            )

        val nationIds = cups.map { it.nation.id!! }.toSet()
        val nations = nationRepository.findAllById(nationIds).associateBy { it.id }

        return cups.map { cup ->
            cup.toGetCupWithChildrenDto(
                races = raceDescriptors[cup.id] ?: emptyList(),
                nation = nations[cup.nation.id]!!.toNationDescriptorDto()
            )
        }
    }

    @Transactional(readOnly = true)
    fun findCupsInNation(
        id: Long,
        filter: CupFilter,
        pageable: PageRequest
    ): Page<GetCupDto> {
        val spec = CupSpecification.withFilter(filter)
        val cups = cupRepository.findAllByNationId(
            cupNationId = id,
            specification = spec,
            pageable = pageable
        )
        val cupIds = cups.content.map { it.id!! }

        val raceIds: Map<Long, List<Long>> = raceRepository
            .findIdsByCupIds(cupIds)
            .groupBy(
                keySelector = { it[0] as Long },
                valueTransform = { it[1] as Long }
            )

        return cups.map { cup ->
            cup.toGetCupDto(
                raceIds = raceIds[cup.id] ?: emptyList(),
                nationId = cup.nation.id!!
            )
        }
    }

    @Transactional
    fun putCup(
        id: Long,
        putCupDto: PutCupDto
    ): Pair<GetCupDto, Boolean> {
        val targetNation: Nation = nationRepository
            .findById(
                putCupDto.nationId
            ).orElseThrow {
                EntityNotFoundException()
            }
        val existingCup = cupRepository.findByIdOrNull(id)
        val cupToSave: Cup = existingCup?.let {
            putCupDto.toEntity(
                originalCup = it,
                nation = targetNation
            )
        } ?: run {
            putCupDto.toNewEntity(
                nation = targetNation
            )
        }

        val savedCup = cupRepository.save(cupToSave)
        return savedCup.toGetCupDto(
            raceIds = emptyList(),
            nationId = savedCup.nation.id!!
        ) to (existingCup == null)
    }

    @Transactional
    fun createCup(
        postCupDto: PostCupDto
    ): GetCupDto {
        val targetNation: Nation = nationRepository
            .findById(
                postCupDto.nationId
            ).orElseThrow {
                EntityNotFoundException()
            }
        val cupToSave = postCupDto.toNewEntity(
            nation = targetNation
        )

        val savedCup = cupRepository.save(cupToSave)
        return savedCup.toGetCupDto(
            raceIds = emptyList(),
            nationId = savedCup.nation.id!!
        )
    }

    @Transactional
    fun deleteCup(id: Long) {
        cupRepository.deleteById(id)
    }
}
