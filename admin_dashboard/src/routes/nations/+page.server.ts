import type { PageServerLoad } from './$types';
import { error } from '@sveltejs/kit';
import type { GetNationResponse } from '$lib/types/nations';

import { env } from "$env/dynamic/private";
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

    const nations: GetNationResponse = await response.json();
    return { nations };
}) satisfies PageServerLoad;
