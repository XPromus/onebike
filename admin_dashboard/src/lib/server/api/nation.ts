import { env } from "$env/dynamic/private";
import type { Nation } from "$lib/types/client/nation.types";
import type { PutNationDto, PostNationExistsDto } from "$lib/types/server/nation.types";

const getURL = (path: string): string => {
    return `${env.API_BASE_URL}${path}`;
}

export const putNation = async (
    payload: Partial<PutNationDto>,
    fetchFn: typeof fetch = fetch
): Promise<Nation> => {
    const url = getURL(env.NATIONS_PATH);
    const response = await fetchFn(
        url, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
        }
    );

    if (!response.ok) throw new Error(`Nation upload failed: ${response.status}`);
    return response.json();
}

export const postCheckIfNationExists = async (
    payload: PostNationExistsDto,
    fetchFn: typeof fetch = fetch
): Promise<boolean> => {
    const url = getURL(env.NATIONS_EXISTS_PATH);
    const response = await fetchFn(
        url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(payload)
        }
    );

    if (!response.ok) throw new Error(`Nation check failed: ${response.status}`);
    return response.json();
}
