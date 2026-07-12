package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.cup.mapper.toEntity
import com.xpromus.onebike_backend.cup.mapper.toGetCupDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupDtoList
import com.xpromus.onebike_backend.cup.mapper.toNewEntity
import com.xpromus.onebike_backend.nation.NationRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CupService(
    private val cupRepository: CupRepository,
    private val nationRepository: NationRepository
) {
    fun getAll(): List<GetCupDto> {
        return cupRepository.findAll().toGetCupDtoList()
    }

    fun getCupsInNation(id: Long): List<GetCupDto> {
        return cupRepository.findAllByCupNationId(id).toGetCupDtoList()
    }

    fun getCupsByName(name: String): List<GetCupDto> {
        return cupRepository.findAllByCupNameLike(name).toGetCupDtoList()
    }

    @Transactional
    fun putCupDto(
        putCupDto: PutCupDto
    ): GetCupDto {
        val targetNation = nationRepository.findById(putCupDto.cupNationId).orElseThrow {
            EntityNotFoundException()
        }

        val cup: Cup = putCupDto.id?.let {
            cupRepository.findById(it).orElse(null)
        }?.let {
            putCupDto.toEntity(
                originalCup = it,
                nation = targetNation
            )
        } ?: run {
            putCupDto.toNewEntity(
                nation = targetNation
            )
        }

        return cupRepository.save(
            cup
        ).toGetCupDto()
    }

    @Transactional
    fun deleteCup(id: Long) {
        cupRepository.deleteById(id)
    }

}
