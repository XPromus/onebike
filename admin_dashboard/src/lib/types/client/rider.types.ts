import type { NationDescriptorDto } from "../server/nation.types";
import type { PlacementDescriptorDto } from "../server/placement.types";
import type { TeamDescriptorDto } from "../server/team.types";

export type Rider = {
    id: number,
    firstName: string,
    lastName: string,
    dateOfBirth: Date,
};

export type RiderWithIds = Rider & {
    placementIds: number[],
    nationId: number,
    teamId?: number,
}

export type RiderWithChildren = Rider & {
    placements: PlacementDescriptorDto[],
    nation: NationDescriptorDto,
    team: TeamDescriptorDto | null,
}

export type RiderEditDto = {
    id?: number,
    firstName: string,
    lastName: string,
    dateOfBirth: Date,
    nationId: number,
    teamId?: number,
}
