import type { PageServerLoad } from './$types';
import { error } from '@sveltejs/kit';
import type { GetNationResponse } from '$lib/api/types/nations';

import { env } from "$env/dynamic/private";
const { API_BASE_URL, NATIONS_PATH } = env;

export const load = (async ({ fetch }) => {
    const res = await fetch(`${API_BASE_URL}${NATIONS_PATH}`)

    if (!res.ok) {
        throw error(res.status, "Failed to fetch item");
    }

    const items: GetNationResponse = await res.json();
    return { items };
}) satisfies PageServerLoad;
