package com.xpromus.onebike_backend.nation

import com.xpromus.onebike_backend.nation.dto.CreateNationDto
import com.xpromus.onebike_backend.nation.dto.GetNationDto
import com.xpromus.onebike_backend.nation.dto.UpdateNationDto
import com.xpromus.onebike_backend.nation.mapper.createNationDtoToNation
import com.xpromus.onebike_backend.nation.mapper.nationToGetNationDtoMapper
import com.xpromus.onebike_backend.nation.mapper.updateNationDtoToNation
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class NationService(
    private val nationRepository: NationRepository
) {

    fun getNations(): List<GetNationDto> {
        val nations = nationRepository.findAll()
        return nations.map {
            nationToGetNationDtoMapper(it)
        }
    }

    fun createNation(
        createNationDto: CreateNationDto
    ): GetNationDto {
        val newNation = createNationDtoToNation(createNationDto)
        return nationToGetNationDtoMapper(
            nationRepository.save(newNation)
        )
    }

    fun updateNation(
        updateNationDto: UpdateNationDto
    ): GetNationDto {
        val updatedNation = updateNationDtoToNation(updateNationDto)
        return nationToGetNationDtoMapper(
            nationRepository.save(updatedNation)
        )
    }

    @Transactional
    fun deleteNation(
        id: Long
    ) {
        nationRepository.deleteById(id)
    }

}
