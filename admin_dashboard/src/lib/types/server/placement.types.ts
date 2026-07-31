import type { components } from "../schema/schema"

export type GetPlacementDto = components["schemas"]["GetPlacementDto"];
export type GetPlacementWithChildrenDto = components["schemas"]["GetPlacementWithChildrenDto"];
export type PlacementDescriptorDto = components["schemas"]["PlacementDescriptorDto"];
export type PostPlacementDto = components["schemas"]["PostPlacementDto"];
export type PutPlacementDto = components["schemas"]["PutPlacementDto"];

export type GetPlacementsResponse = GetPlacementDto[];
export type GetPlacementsWithChildrenResponse = GetPlacementWithChildrenDto[];
