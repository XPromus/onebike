package com.xpromus.onebike_backend.nation

import org.springframework.data.jpa.repository.JpaRepository

interface NationRepository : JpaRepository<Nation, Long> {

    fun existsNationByLongNameIsOrShortNameIsOrFlagEmojiIs(
        longName: String,
        shortName: String,
        flagEmoji: String
    ): Boolean

}
