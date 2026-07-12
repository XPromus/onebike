package com.xpromus.onebike_backend.race

import com.xpromus.onebike_backend.cup.Cup
import com.xpromus.onebike_backend.cup.CupRepository
import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.race.dto.GetRaceDto
import com.xpromus.onebike_backend.race.dto.PutRaceDto
import com.xpromus.onebike_backend.race.mapper.toEntity
import com.xpromus.onebike_backend.race.mapper.toGetRaceDto
import com.xpromus.onebike_backend.race.mapper.toGetRaceDtoList
import com.xpromus.onebike_backend.race.mapper.toNewEntity
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class RaceService(
    private val raceRepository: RaceRepository,
    private val nationRepository: NationRepository,
    private val cupRepository: CupRepository
) {

    fun getAllRaces(): List<GetRaceDto> {
        return raceRepository.findAll().toGetRaceDtoList()
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
