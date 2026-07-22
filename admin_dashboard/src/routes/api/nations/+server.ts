import { fail, json } from '@sveltejs/kit';
import type { RequestHandler } from './$types';
import { postCheckIfNationExists, putNation } from '$lib/server/api/nation';
import type { PostNationExistsDto, PutNationDto } from '$lib/types/server/nation.types';

export const PUT: RequestHandler = async ({
    request, fetch
}) => {
    const payload: PutNationDto = await request.json();

    try {
        const result = await putNation(payload, fetch);
        return json(result);
    } catch (e) {
        return fail(500, { error: "(Server) PUT failed" });
    }
}

export const POST: RequestHandler = async ({
    request, fetch
}) => {
    const payload: PostNationExistsDto = await request.json();

    try {
        const result = await postCheckIfNationExists(payload, fetch);
        return json(result);
    } catch (e) {
        return fail(500, { error: "(Server) POST failed" });
    }
}
