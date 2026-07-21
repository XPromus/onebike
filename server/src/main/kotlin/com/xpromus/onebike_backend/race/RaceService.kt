package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.CupRepository
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.GetRaceWithChildrenDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import com.xpromus.onebike_backend.race.mapper.toEntity
import com.xpromus.onebike_backend.race.mapper.toGetRaceDto
import com.xpromus.onebike_backend.race.mapper.toGetRaceDtoList
import com.xpromus.onebike_backend.race.mapper.toGetRaceWithChildrenDtoList
import com.xpromus.onebike_backend.race.mapper.toNewEntity
import com.xpromus.onebike_backend.util.SortDirection
import com.xpromus.onebike_backend.util.toSortDir
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RaceService(
    private val raceRepository: RaceRepository,
    private val nationRepository: NationRepository,
    private val cupRepository: CupRepository
) {

    @Transactional(readOnly = true)
    fun getAllRaces(
        sortBy: String,
        sortDirection: SortDirection
    ): List<GetRaceDto> {
        return raceRepository.findAll(
            Sort.by(
                sortDirection.toSortDir(),
                sortBy
            )
        ).toGetRaceDtoList()
    }

    @Transactional(readOnly = true)
    fun getRacesWithChildren(
        sortBy: String,
        sortDirection: SortDirection
    ): List<GetRaceWithChildrenDto> {
        return raceRepository.findAll(
            Sort.by(
                sortDirection.toSortDir(),
                sortBy
            )
        ).toGetRaceWithChildrenDtoList()
    }

    @Transactional
    fun putRace(
        putRaceDto: PutRaceDto
    ): GetRaceDto {
        val targetNation: Nation = nationRepository.findById(putRaceDto.nationId).orElseThrow {
            EntityNotFoundException()
        }
        val targetCup: Cup? = putRaceDto.cupId?.let {
            cupRepository.findById(it).orElse(null)
        }

        val race: Race = putRaceDto.id?.let {
            raceRepository.findById(it).orElse(null)
        }?.let {
            putRaceDto.toEntity(
                original = it,
                nation = targetNation,
                cup = targetCup
            )
        } ?: run {
            putRaceDto.toNewEntity(
                nation = targetNation,
                cup = targetCup
            )
        }

        return raceRepository.save(
            race
        ).toGetRaceDto()
    }

    @Transactional
    fun deleteRace(
        id: Long
    ) {
        raceRepository.deleteById(id)
    }

}
