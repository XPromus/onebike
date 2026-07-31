import type { components } from "../schema/schema";

export type GetCupDto = components["schemas"]["GetCupDto"];
export type GetCupWithChildrenDto = components["schemas"]["GetCupWithChildrenDto"];
export type CupDescriptorDto = components["schemas"]["CupDescriptorDto"];
export type PostCupDto = components["schemas"]["PostCupDto"];
export type PutCupDto = components["schemas"]["PutCupDto"];

export type GetCupsResponse = GetCupDto[];
export type GetCupsWithChildrenResponse = GetCupWithChildrenDto[];
