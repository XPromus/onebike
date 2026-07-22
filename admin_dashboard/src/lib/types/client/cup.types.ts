import type { NationDescriptorDto } from "../server/nation.types"
import type { RaceDescriptorDto } from "../server/race.types"

export type Cup = {
    id: number,
    cupName: string,
    url?: string,
}

export type CupWithIds = Cup & {
    raceIds: number[],
    nationId: number,
}

export type CupWithChildren = Cup & {
    races: RaceDescriptorDto[],
    nation: NationDescriptorDto
}

export type CupEditDto = {
    id?: number,
    cupName: string,
    url?: string,
    nationId: number,
}
