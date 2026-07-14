package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.GetNationWithChildrenDto
import com.xpromus.onebike_backend.nation.dto.PostNationExistsDto
import com.xpromus.onebike_backend.nation.dto.PutNationDto
import com.xpromus.onebike_backend.nation.mapper.toEntity
import com.xpromus.onebike_backend.nation.mapper.toGetDtoList
import com.xpromus.onebike_backend.nation.mapper.toGetWithChildrenDto
import com.xpromus.onebike_backend.nation.mapper.toNewEntity
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class NationService(
    private val nationRepository: NationRepository
) {

    fun getNations(): List<GetNationDto> {
        val nations = nationRepository.findAll(
            Sort.by("longName").ascending()
        )
        return nations.toGetDtoList()
    }

    fun checkIfNationExists(
        postNationExistsDto: PostNationExistsDto
    ): Boolean {
        return nationRepository.existsNationByLongNameIsOrShortNameIsOrFlagEmojiIs(
            longName = postNationExistsDto.longName,
            shortName = postNationExistsDto.shortName,
            flagEmoji = postNationExistsDto.flagEmoji
        )
    }

    fun putNation(
        putNationDto: PutNationDto
    ): GetNationWithChildrenDto {
        val nation: Nation = putNationDto.id?.let {
            nationRepository.findById(it).orElse(null)
        }?.let {
            putNationDto.toEntity(
                original = it
            )
        } ?: run {
            putNationDto.toNewEntity()
        }

        return nationRepository.save(
            nation
        ).toGetWithChildrenDto()
    }

    @Transactional
    fun deleteNation(
        id: Long
    ) {
        nationRepository.deleteById(id)
    }

}
