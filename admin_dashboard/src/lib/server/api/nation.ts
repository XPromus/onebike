import { env } from "$env/dynamic/private";
import type { Nation } from "$lib/types/client/nation.types";
import type { PageGetNationDto, PutNationDto } from "$lib/types/server/nation.types";
import { getPage, type PageParams } from "./pagination";

export type NationListParams = PageParams & {
    longName?: string;
    shortName?: string;
    id?: number;
};

const getURL = (path: string): string => {
    return `${env.API_BASE_URL}${path}`;
}

export const getNations = async (
    params: NationListParams,
    fetchFn: typeof fetch = fetch,
): Promise<PageGetNationDto> => {
    return getPage<PageGetNationDto>(
        `${env.API_BASE_URL}${env.NATIONS_PATH}`,
        params,
        {
            longName: params.longName,
            shortName: params.shortName,
            id: params.id
        },
        fetchFn
    );
}

export const putNation = async (
    payload: Partial<PutNationDto>,
    fetchFn: typeof fetch = fetch
): Promise<Nation> => {
    const url = getURL(env.NATIONS_PATH!!);
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
