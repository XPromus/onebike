import type { NationDescriptorDto } from "./nation.types";
import type { RiderDescriptorDto } from "./rider.types";

export type GetTeamDto = {
    id: number,
    teamName: string,
    shortName: string,
    teamDescription: string,
    nationalityId: number,
    riderIds: number[],
}

export type GetTeamWithChildrenDto = {
    id: number,
    teamName: string,
    shortName: string,
    teamDescription: string,
    nation: NationDescriptorDto,
    riders: RiderDescriptorDto[],
}

export type PutTeamDto = {
    id?: number,
    teamName: string,
    shortName: string,
    teamDescription: string,
    nationId: number,
}

export type TeamDescriptorDto = {
    id: number,
    teamName: string,
    shortName: string,
    teamDescription: string,
}

export type GetTeamResponse = GetTeamDto[];
export type GetTeamWithChildrenResponse = GetTeamWithChildrenDto[];
