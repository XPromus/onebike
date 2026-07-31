import type { components } from "../schema/schema"

export type GetTeamDto = components["schemas"]["GetTeamDto"];
export type GetTeamWithChildrenDto = components["schemas"]["GetTeamWithChildrenDto"];
export type TeamDescriptorDto = components["schemas"]["TeamDescriptorDto"];
export type PostTeamDto = components["schemas"]["PostTeamDto"];
export type PutTeamDto = components["schemas"]["PutTeamDto"];

export type GetTeamResponse = GetTeamDto[];
export type GetTeamWithChildrenResponse = GetTeamWithChildrenDto[];
