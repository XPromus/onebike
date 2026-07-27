package com.xpromus.onebike_backend

import com.xpromus.onebike_backend.nation.Nation
import com.xpromus.onebike_backend.nation.NationRepository
import com.xpromus.onebike_backend.nation.NationService
import com.xpromus.onebike_backend.nation.dto.PutNationDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
@Testcontainers
class NationIntegrationTests {

    companion object {
        @Container
        @JvmStatic
        val postgres = PostgreSQLContainer(
            DockerImageName.parse("postgres:16-alpine")
        )

        @DynamicPropertySource
        @JvmStatic
        fun properties(
            registry: DynamicPropertyRegistry
        ) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
        }
    }

    @Autowired lateinit var nationService: NationService
    @Autowired lateinit var nationRepository: NationRepository

    @BeforeEach
    fun setup() {
        nationRepository.deleteAll()
    }

//    @Test
//    fun `getNations returns empty list when no nations exist`() {
//        val result = nationService.getNations()
//        assertTrue(result.isEmpty())
//    }

//    @Test
//    fun `updateNation modifies existing nation`() {
//        val saved = nationRepository.save(
//            Nation(
//                longName = "Germany",
//                shortName = "GER",
//                flagEmoji = "🇩🇪",
//            )
//        )
//        val putNationDto = PutNationDto(
//            id = saved.id!!,
//            longName = "Spain",
//            shortName = "ESP",
//            flagEmoji = "🇪🇸"
//        )
//        val result = nationService.putNation(putNationDto)
//
//        assertEquals(putNationDto.longName, result.longName)
//        assertEquals(putNationDto.shortName, result.shortName)
//        assertEquals(putNationDto.flagEmoji, result.flagEmoji)
//
//        val persisted = nationRepository.findById(saved.id!!).get()
//        assertEquals(putNationDto.longName, persisted.longName)
//    }
//
//    @Test
//    fun `deleteNation removes nation from database`() {
//        val saved = nationRepository.save(
//            Nation(
//                longName = "Germany",
//                shortName = "GER",
//                flagEmoji = "🇩🇪",
//            )
//        )
//
//        nationService.deleteNation(saved.id!!)
//        assertTrue(nationRepository.findById(saved.id!!).isEmpty)
//    }

}
