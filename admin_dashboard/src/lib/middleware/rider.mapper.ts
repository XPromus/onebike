import type { EntityContent } from "$lib/types/entity";
import type { RiderWithChildren } from "$lib/types/riders";

export const riderToEntity = (
    rider: RiderWithChildren
): EntityContent => {
    return {
        values: [
            `${rider.firstName} ${rider.lastName}`,
            `${rider.nation.flagEmoji} (${rider.nation.shortName})`,
            rider.team != null ? rider.team.teamName : "-",
        ]
    } satisfies EntityContent;
}
