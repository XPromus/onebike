package com.xpromus.onebike_backend.rider

import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.rider.dto.GetRiderDto
import com.xpromus.onebike_backend.rider.dto.GetRiderWithChildrenDto
import com.xpromus.onebike_backend.rider.dto.PutRiderDto
import com.xpromus.onebike_backend.rider.mapper.toEntity
import com.xpromus.onebike_backend.rider.mapper.toGetRiderDto
import com.xpromus.onebike_backend.rider.mapper.toGetRiderDtoList
import com.xpromus.onebike_backend.rider.mapper.toGetRiderWithChildrenDtoList
import com.xpromus.onebike_backend.rider.mapper.toNewEntity
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class RiderService(
    private val riderRepository: RiderRepository,
    private val nationRepository: NationRepository
) {

    fun getRiders(): List<GetRiderDto> {
        return riderRepository.findAll().toGetRiderDtoList()
    }

    fun getRidersWithChildren(): List<GetRiderWithChildrenDto> {
        return riderRepository.findAll().toGetRiderWithChildrenDtoList()
    }

    @Transactional
    fun putRider(
        putRiderDto: PutRiderDto
    ): GetRiderDto {
        val nation = nationRepository.findById(putRiderDto.nationId).orElseThrow {
            EntityNotFoundException()
        }
        val rider: Rider = putRiderDto.id?.let {
            riderRepository.findById(it).orElse(null)
        }?.let {
            putRiderDto.toEntity(
                original = it,
                nation = nation
            )
        } ?: run {
            putRiderDto.toNewEntity(
                nation = nation
            )
        }

        return riderRepository.save(
            rider
        ).toGetRiderDto()
    }

    @Transactional
    fun deleteRider(
        id: Long
    ) {
        riderRepository.deleteById(id)
    }

}
