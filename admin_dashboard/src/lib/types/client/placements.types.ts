import type { RaceDescriptorDto } from "../server/race.types"
import type { RiderDescriptorDto } from "../server/rider.types"

export type Placement = {
    id: number,
    points: number,
    finishTimeInSceconds: number,
    finishStatus: string,
}

export type PlacementWithIds = Placement & {
    riderId: number,
    raceId: number,
}

export type PlacementWithChildren = Placement & {
    rider: RiderDescriptorDto,
    race: RaceDescriptorDto,
}

export type PlacementEditDto = {
    id?: number,
    points: number,
    finishTimeInSceconds: number,
    finishStatus: string,
    riderId: number,
    raceId: number,
}
