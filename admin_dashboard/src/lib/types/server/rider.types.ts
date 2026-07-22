import type { NationDescriptorDto } from "./nation.types";
import type { PlacementDescriptorDto } from "./placement.types";
import type { TeamDescriptorDto } from "./team.types";

export type GetRiderDto = {
    id: number,
    firstName: string,
    lastName: string,
    dateOfBirth: string,
    placementIds: number[],
    nationId: number,
    teamId: number | null,
}

export type GetRiderWithChildrenDto = {
    id: number,
    firstName: string,
    lastName: string,
    dateOfBirth: string,
    placements: PlacementDescriptorDto[],
    nation: NationDescriptorDto,
    team: TeamDescriptorDto | null,
}

export type PutRiderDto = {
    id?: number,
    firstName: string,
    lastName: string,
    dateOfBirth: string,
    nationId: number,
    teamId?: number,
}

export type RiderDescriptorDto = {
    id: number,
    firstName: string,
    lastName: string,
    dateOfBirth: string,
}

export type GetRidersResponse = GetRiderDto[];
export type GetRidersWithChildrenResponse = GetRiderWithChildrenDto[];
