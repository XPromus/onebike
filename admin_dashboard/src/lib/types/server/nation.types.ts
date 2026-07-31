import type { components } from "../schema/schema";

export type GetNationDto = components["schemas"]["GetNationDto"];
export type GetNationWithChildrenDto = components["schemas"]["GetNationWithChildrenDto"];
export type NationDescriptorDto = components["schemas"]["NationDescriptorDto"];
export type PostNationDto = components["schemas"]["PostNationDto"];
export type PutNationDto = components["schemas"]["PutNationDto"];

export type GetNationResponse = GetNationDto[];
