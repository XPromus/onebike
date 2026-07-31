import type { components } from "../schema/schema"

export type GetRaceDto = components["schemas"]["GetRaceDto"];
export type GetRaceWithChildrenDto = components["schemas"]["GetRaceWithChildrenDto"];
export type RaceDescriptorDto = components["schemas"]["RaceDescriptorDto"];
export type PostRaceDto = components["schemas"]["PostRaceDto"];
export type PutRaceDto = components["schemas"]["PutRaceDto"];

export type GetRacesResponse = GetRaceDto[];
export type GetRacesWithChildrenResponse = GetRaceWithChildrenDto[];
