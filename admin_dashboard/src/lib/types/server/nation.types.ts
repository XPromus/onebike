import type { components } from "../schema/schema";

export type GetNationDto = components["schemas"]["GetNationDto"];
export type PageGetNationDto = components["schemas"]["PageGetCupDto"];
export type GetNationWithChildrenDto = components["schemas"]["GetNationWithChildrenDto"];
export type PageGetNatonWithChildrenDto = components["schemas"]["PageGetCupWithChildrenDto"];
export type NationDescriptorDto = components["schemas"]["NationDescriptorDto"];
export type PostNationDto = components["schemas"]["PostNationDto"];
export type PutNationDto = components["schemas"]["PutNationDto"];

export type GetNationResponse = GetNationDto[];
