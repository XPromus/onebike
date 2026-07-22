import type { CupDescriptorDto } from "../server/cup.types"
import type { NationDescriptorDto } from "../server/nation.types"
import type { PlacementDescriptorDto } from "../server/placement.types"

export type Race = {
    id: number,
    raceName: string,
    lengthInKm: number,
    raceDate: string,
    startTime: string,
}

export type RaceWithIds = Race & {
    nationId: number,
    cupId?: number,
    placementIds: number[],
}

export type RaceWithChildren = Race & {
    nation: NationDescriptorDto,
    cup?: CupDescriptorDto,
    placements: PlacementDescriptorDto[],
}

export type RaceEditDto = {
    id?: number,
    raceName: string,
    lengthInKm: number,
    raceDate: string,
    startTime: string,
    nationId: number,
    cupId?: number,
}
