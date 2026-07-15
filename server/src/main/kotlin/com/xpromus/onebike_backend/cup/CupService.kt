package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.cup.mapper.toEntity
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDtoList
import com.xpromus.onebike_backend.cup.mapper.toNewEntity
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.util.SortDirection
import com.xpromus.onebike_backend.util.toSortDir
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CupService(
    private val cupRepository: CupRepository,
    private val nationRepository: NationRepository
) {
    fun getAll(
        sortBy: String,
        sortDirection: SortDirection
    ): List<GetCupWithChildrenDto> {
        return cupRepository.findAll(
            Sort.by(
                sortDirection.toSortDir(),
                sortBy
            )
        ).toGetCupWithChildrenDtoList()
    }

    fun getCupsInNation(
        id: Long,
        sortBy: String,
        sortDirection: SortDirection
    ): List<GetCupWithChildrenDto> {
        return cupRepository.findAllByNationId(
            cupNationId = id,
            sort = Sort.by(
                sortDirection.toSortDir(),
                sortBy
            )
        ).toGetCupWithChildrenDtoList()
    }

    fun getCupsByName(
        name: String,
        sortBy: String,
        sortDirection: SortDirection
    ): List<GetCupWithChildrenDto> {
        return cupRepository.findAllByCupNameLike(
            cupName = name,
            sort = Sort.by(
                sortDirection.toSortDir(),
                sortBy
            )
        ).toGetCupWithChildrenDtoList()
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
