import type { RaceDescriptorDto } from "./race.types"
import type { RiderDescriptorDto } from "./rider.types"

export type GetPlacementDto = {
    id: number,
    points: number,
    finishTimeInSceconds: number,
    finishStatus: string,
    riderId: number,
    raceId: number,
}

export type GetPlacementWithChildrenDto = {
    id: number,
    points: number,
    finishTimeInSceconds: number,
    finishStatus: string,
    rider: RiderDescriptorDto,
    race: RaceDescriptorDto,
}

export type PlacementDescriptorDto = {
    id: number,
    points: number,
    finishTimeInSeconds: number,
    finishStatus: number,
    raceId: number,
}

export type PutPlacementDto = {
    id?: number,
    points: number,
    finishTimeInSeconds: number,
    finishStatus: string,
    riderId: number,
    raceId: number,
}

export type GetPlacementsResponse = GetPlacementDto[];
export type GetPlacementsWithChildrenResponse = GetPlacementWithChildrenDto[];
