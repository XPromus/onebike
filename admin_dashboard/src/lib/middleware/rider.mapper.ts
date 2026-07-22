import type { RiderWithChildren, RiderWithIds } from "$lib/types/client/rider.types";
import type { GetRiderDto, GetRiderWithChildrenDto } from "$lib/types/server/rider.types";

export const toRiderWithIds = (
    rider: GetRiderDto
): RiderWithIds => {
    return {
        id: rider.id,
        firstName: rider.firstName,
        lastName: rider.lastName,
        dateOfBirth: new Date(rider.dateOfBirth),
        placementIds: rider.placementIds,
        nationId: rider.nationId
    } satisfies RiderWithIds;
}

export const toRiderWithChildren = (
    rider: GetRiderWithChildrenDto
): RiderWithChildren => {
    return {
        id: rider.id,
        firstName: rider.firstName,
        lastName: rider.lastName,
        dateOfBirth: new Date(rider.dateOfBirth),
        placements: rider.placements,
        nation: rider.nation,
        team: rider.team
    } satisfies RiderWithChildren;
}

export const riderWithChildrenToRiderWithIds = (
    rider: RiderWithChildren
): RiderWithIds => {
    return {
        id: rider.id,
        firstName: rider.firstName,
        lastName: rider.lastName,
        dateOfBirth: rider.dateOfBirth,
        placementIds: rider.placements.map(
            (placement) => {
                return placement.id
            }
        ),
        nationId: rider.nation.id
    } satisfies RiderWithIds
}
