import type { NationDescriptorDto } from "./nations";
import type { TeamDescriptorDto } from "./teams";

export type Rider = {
    id: number,
    firstName: string,
    lastName: string,
    placementIDs: number[],
    // nationId: number,
};

export type GetRiderResponse = Rider & {
    nationId: number
};

export type RiderWithChildren = Rider & {
    nation: NationDescriptorDto,
    team: TeamDescriptorDto?,
}

export type GetRiderWithChildrenResponse = RiderWithChildren[];
