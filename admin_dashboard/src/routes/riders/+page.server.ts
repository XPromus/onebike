import type { PageServerLoad } from './$types';
import { env } from "$env/dynamic/private";
import { error } from '@sveltejs/kit';
import type { Rider, RiderWithChildren } from '$lib/types/client/rider.types';
import { toRiderWithChildren } from '$lib/middleware/rider.mapper';
import type { GetNationResponse } from '$lib/types/server/nation.types';
import type { GetRidersWithChildrenResponse } from '$lib/types/server/rider.types';
import type { GetTeamResponse } from '$lib/types/server/team.types';
const { API_BASE_URL, RIDERS_PATH, RIDERS_WITH_CHILDREN_PATH, NATIONS_PATH, TEAMS_PATH } = env;

export const load = (async ({ fetch }) => {
    const ridersResponse = await fetch(
        `${API_BASE_URL}${RIDERS_PATH}${RIDERS_WITH_CHILDREN_PATH}`
    );
    const nationsResponse = await fetch(
        `${API_BASE_URL}${NATIONS_PATH}`
    );
    const teamsResponse = await fetch(
        `${API_BASE_URL}${TEAMS_PATH}`
    );

    if (!ridersResponse.ok) {
        throw error(
            ridersResponse.status, 
            "Failed to fetch riders." 
        );
    }

    if (!nationsResponse.ok) {
        throw error(
            nationsResponse.status,
            "Failed to fetch nations."
        );
    }

    if (!teamsResponse.ok) {
        throw error(
            teamsResponse.status,
            "Faled to fetch teams."
        );
    }

    const ridersWithChildren: GetRidersWithChildrenResponse = await ridersResponse.json();
    const riders: RiderWithChildren[] = ridersWithChildren.map((rider) => toRiderWithChildren(rider));
    const nations: GetNationResponse = await nationsResponse.json();
    const teams: GetTeamResponse = await teamsResponse.json();
    
    return { 
        riders,
        nations,
        teams,
    };
}) satisfies PageServerLoad;
