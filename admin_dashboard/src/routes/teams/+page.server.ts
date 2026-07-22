import type { PageServerLoad } from './$types';
import { env } from "$env/dynamic/private";
import { error } from '@sveltejs/kit';
import type { GetNationResponse } from '$lib/types/server/nation.types';
import type { GetTeamWithChildrenResponse } from '$lib/types/server/team.types';
const {
    API_BASE_URL, TEAMS_PATH, TEAMS_WITH_CHILDREN_PATH, NATIONS_PATH
} = env;

export const load = (async ({ fetch }) => {
    const teamsResponse = await fetch(
        `${API_BASE_URL}${TEAMS_PATH}${TEAMS_WITH_CHILDREN_PATH}`
    );
    const nationsResponse = await fetch(
        `${API_BASE_URL}${NATIONS_PATH}`
    );

    if (!teamsResponse.ok) {
        throw error(
            teamsResponse.status,
            "Failed to fetch teams."
        );
    }

    if (!nationsResponse.ok) {
        throw error(
            nationsResponse.status,
            "Failed to fetch nation."
        );
    }

    const teams: GetTeamWithChildrenResponse = await teamsResponse.json();
    const nations: GetNationResponse = await nationsResponse.json();

    return {
        teams,
        nations
    };
}) satisfies PageServerLoad;