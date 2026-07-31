import type { components } from "../schema/schema"

export type GetRiderDto = components["schemas"]["GetRiderDto"];
export type GetRiderWithChildrenDto = components["schemas"]["GetRiderWithChildrenDto"];
export type RiderDescriptorDto = components["schemas"]["RiderDescriptorDto"];
export type PostRiderDto = components["schemas"]["PostRiderDto"];
export type PutRiderDto = components["schemas"]["PutRiderDto"];

export type GetRidersResponse = GetRiderDto[];
export type GetRidersWithChildrenResponse = GetRiderWithChildrenDto[];
