package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.rider.dto.CreateRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.UpdateRiderDto
import com.xpromus.onebike_backend.rider.mapper.createRiderDtoToRider
import com.xpromus.onebike_backend.rider.mapper.riderToGetRiderDto
import com.xpromus.onebike_backend.rider.mapper.updateRiderDtoToRider
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class RiderService(
    private val riderRepository: RiderRepository,
    private val nationRepository: NationRepository
) {

    fun getRiders(): List<GetRiderDto> {
        return riderRepository.findAll().map {
            riderToGetRiderDto(it)
        }
    }

    @Transactional
    fun createRider(
        createRiderDto: CreateRiderDto
    ): GetRiderDto {
        val riderNation = nationRepository.findById(createRiderDto.nationId).orElseThrow {
            EntityNotFoundException()
        }
        val newRider = createRiderDtoToRider(createRiderDto, riderNation)
        return riderToGetRiderDto(
            riderRepository.save(newRider)
        )
    }

    @Transactional
    fun updateRider(
        updateRiderDto: UpdateRiderDto
    ): GetRiderDto {
        val riderNation = nationRepository.findById(updateRiderDto.nationId).orElseThrow {
            EntityNotFoundException()
        }
        val updatedRider = updateRiderDtoToRider(updateRiderDto, riderNation)
        return riderToGetRiderDto(
            riderRepository.save(updatedRider)
        )
    }

    @Transactional
    fun deleteRider(
        id: Long
    ) {
        riderRepository.deleteById(id)
    }

}
