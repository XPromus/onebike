import type { NationDescriptorDto } from "../server/nation.types"
import type { RiderDescriptorDto } from "../server/rider.types"

export type Team = {
    id: number,
    teamName: string,
    shortName: string,
    teamDescription: string,
}

export type TeamWithIds = Team & {
    nationalityId: number,
    riderIds: number[],
}

export type TeamWithChildren = Team & {
    nation: NationDescriptorDto,
    riders: RiderDescriptorDto[],
}

export type TeamEditDto = {
    id?: number,
    teamName: string,
    shortName: string,
    teamDescription: string,
    nationalityId: number,
}
