package com.xpromus.onebike_backend.cup

import com.xpromus.onebike_backend.cup.dto.GetCupDto
import com.xpromus.onebike_backend.cup.dto.GetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.dto.PutCupDto
import com.xpromus.onebike_backend.cup.mapper.toEntity
import com.xpromus.onebike_backend.cup.mapper.toGetCupDtoList
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDto
import com.xpromus.onebike_backend.cup.mapper.toGetCupWithChildrenDtoList
import com.xpromus.onebike_backend.cup.mapper.toNewEntity
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.util.SortDirection
import com.xpromus.onebike_backend.util.toSortDir
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CupService(
    private val cupRepository: CupRepository,
    private val nationRepository: NationRepository
) {

    fun getCups(
        sortBy: String,
        sortDirection: SortDirection
    ): List<GetCupDto> {
        return cupRepository.findAll(
            Sort.by(
                sortDirection.toSortDir(),
                sortBy
            )
        ).toGetCupDtoList()
    }

    @Transactional(readOnly = true)
    fun getCupWithChildren(
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

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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
                putCupDto.nationId
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
