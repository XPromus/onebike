import type { PageServerLoad } from './$types';
import { error } from '@sveltejs/kit';
import { env } from "$env/dynamic/private";
import type { Nation } from '$lib/types/client/nation.types';
import { getNationDtoToNation } from '$lib/middleware/nation.mapper';
import type { GetNationResponse } from '$lib/types/server/nation.types';
const { API_BASE_URL, NATIONS_PATH } = env;

export const load = (async ({ fetch }) => {
    const response = await fetch(
        `${API_BASE_URL}${NATIONS_PATH}`
    );

    if (!response.ok) {
        throw error(
            response.status, 
            "Failed to fetch nations."
        );
    }

    const getNationResponse: GetNationResponse = await response.json();
    const nations: Nation[] = getNationResponse.map((nation) => getNationDtoToNation(nation));
    return { nations };
}) satisfies PageServerLoad;
