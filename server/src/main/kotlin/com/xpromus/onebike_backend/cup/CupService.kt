package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.cup.mapper.toEntity
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDtoList
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
    fun getAll(): List<GetCupWithChildrenDto> {
        return cupRepository.findAll().toGetCupWithChildrenDtoList()
    }

    fun getCupsInNation(id: Long): List<GetCupWithChildrenDto> {
        return cupRepository.findAllByNationId(id).toGetCupWithChildrenDtoList()
    }

    fun getCupsByName(name: String): List<GetCupWithChildrenDto> {
        return cupRepository.findAllByCupNameLike(name).toGetCupWithChildrenDtoList()
    }

    @Transactional
    fun putCupDto(
        putCupDto: PutCupDto
    ): GetCupWithChildrenDto {
        val targetNation = nationRepository
            .findById(
                putCupDto.cupNationId
            ).orElseThrow {
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
        ).toGetCupWithChildrenDto()
    }

    @Transactional
    fun deleteCup(id: Long) {
        cupRepository.deleteById(id)
    }

}
