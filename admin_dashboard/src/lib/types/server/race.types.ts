import type { CupDescriptorDto } from "./cup.types"
import type { NationDescriptorDto } from "./nation.types"
import type { PlacementDescriptorDto } from "./placement.types"

export type GetRaceDto = {
    id: number,
    raceName: string,
    lengthInKm: number,
    raceDate: string,
    startTime: string,
    nationId: number,
    cupId: number | null,
    placementIds: number[],
}

export type GetRaceWithChildrenDto = {
    id: number,
    raceName: string,
    lengthInKm: number,
    raceDate: string,
    startTime: string,
    nation: NationDescriptorDto,
    cup: CupDescriptorDto | null,
    placements: PlacementDescriptorDto[],
}

export type RaceDescriptorDto = {
    id: number,
    raceName: string,
    lengthInKm: number,
    raceDate: string,
    startTime: string,
}

export type PutRaceDto = {
    id?: number,
    raceName: string,
    lengthInKm: number,
    raceDate: string,
    startTime: string,
    nationId: number,
    cupId?: number,
}

export type GetRacesResponse = GetRaceDto[];
export type GetRacesWithChildrenResponse = GetRaceWithChildrenDto[];
