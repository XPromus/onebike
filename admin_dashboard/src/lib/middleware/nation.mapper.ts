import type { Nation, NationWithIds } from "$lib/types/client/nation.types";
import type { GetNationDto, GetNationWithChildrenDto } from "$lib/types/server/nation.types";

export const getNationDtoToNation = (
    nation: GetNationDto
): Nation => {
    return {
        id: nation.id,
        longName: nation.longName,
        shortName: nation.shortName,
        flagEmoji: nation.flagEmoji,
    } satisfies Nation
}

export const getNationWithChildrenDtoToNationWithIds = (
    nation: GetNationWithChildrenDto
): NationWithIds => {
    return {
        id: nation.id,
        longName: nation.longName,
        shortName: nation.shortName,
        flagEmoji: nation.flagEmoji,
        riderIds: nation.raceIds,
        cupIds: nation.cupIds,
        raceIds: nation.raceIds
    } satisfies NationWithIds
}
