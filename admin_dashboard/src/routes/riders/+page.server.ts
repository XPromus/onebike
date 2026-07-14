import type { PageServerLoad } from './$types';
import { env } from "$env/dynamic/private";
import { error } from '@sveltejs/kit';
import type { GetRiderWithChildrenResponse } from '$lib/types/riders';
const { API_BASE_URL, RIDERS_PATH, RIDERS_WITH_CHILDREN_PATH } = env;

export const load = (async ({ fetch }) => {
    const response = await fetch(
        `${API_BASE_URL}${RIDERS_PATH}${RIDERS_WITH_CHILDREN_PATH}`
    );

    if (!response.ok) {
        throw error(
            response.status, 
            "Failed to fetch riders." 
        );
    }

    const riders: GetRiderWithChildrenResponse = await response.json();
    return { riders };
}) satisfies PageServerLoad;
