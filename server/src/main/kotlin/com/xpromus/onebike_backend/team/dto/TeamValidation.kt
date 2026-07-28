package com.xpromus.onebike_backend.team.dto

object TeamValidation {
    const val TEAM_NAME_SIZE_MIN = 1
    const val TEAM_NAME_SIZE_MAX = 200
    const val SHORT_NAME_SIZE_MIN = 1
    const val SHORT_NAME_SIZE_MAX = 5
    const val TEAM_DESCRIPTION_SIZE_MAX = 500

    const val TEAM_NAME_SIZE_MESSAGE = "Team name can not exceed $TEAM_NAME_SIZE_MAX characters"
    const val TEAM_NAME_PATTERN_MESSAGE = "Team name contains invalid characters"
    const val TEAM_NAME_FILTER_SIZE_MESSAGE = "Team name filter can not exceed $TEAM_NAME_SIZE_MAX characters"
    const val TEAM_NAME_FILTER_PATTERN_MESSAGE = "Team name filter contains invalid characters"

    const val SHORT_NAME_SIZE_MESSAGE = "Short name of a team must be between $SHORT_NAME_SIZE_MIN and $SHORT_NAME_SIZE_MAX characters"
    const val SHORT_NAME_PATTERN_MESSAGE = "Short team name contains invalid characters"
    const val SHORT_NAME_FILTER_SIZE_MESSAGE = "Short name filter can not exceed $SHORT_NAME_SIZE_MAX characters"
    const val SHORT_NAME_FILTER_PATTERN_MESSAGE = "Short name filter contains invalid characters"

    const val TEAM_DESCRIPTION_SIZE_MESSAGE = "Team description can not exceed $TEAM_DESCRIPTION_SIZE_MAX characters"

    const val NATION_ID_NOT_NULL_MESSAGE = "Team needs a nation id"
    const val NATION_ID_POSITIVE_MESSAGE = "Nation id in team must be positive"
}
