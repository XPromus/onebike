import type { NationDescriptorDto } from "./nation.types"
import type { RaceDescriptorDto } from "./race.types"

export type GetCupDto = {
    id: number,
    cupName: string,
    url: string | null,
    raceIds: number[],
    nationId: number,
}

export type GetCupWithChildrenDto = {
    id: number,
    cupName: string,
    url: string | null,
    races: RaceDescriptorDto[],
    nation: NationDescriptorDto
}

export type CupDescriptorDto = {
    id: number,
    cupName: string,
    url: string | null,
}

export type PutCupDto = {
    id?: number,
    cupName: string,
    url?: string,
    nationId: number,
}

export type GetCupsResponse = GetCupDto[];
export type GetCupsWithChildrenResponse = GetCupWithChildrenDto[];
