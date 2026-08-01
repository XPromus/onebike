import { parseErrorResponse } from "./errors";

export type SortDir = "ASCENDING" | "DESCENDING";

export type PageParams = {
    page?: number;
    pageSize?: number;
    sortBy?: string;
    sortDir?: SortDir;
};

export const readEnum = <T extends string>(
    value: string | null, 
    allowed: readonly T[], 
    fallback: T
): T => {
    return allowed.includes(value as T) ? (value as T) : fallback;
}

export const readInt = (
    value: string | null, 
    fallback: number
): number => {
    const parsed = value === null ? NaN : parseInt(value, 10);
    return Number.isNaN(parsed) ? fallback : parsed;
}

export const getPage = async <T>(
    url: string, 
    params: PageParams, 
    filter?: Record<string, string|number|undefined>, 
    fetchFn: typeof fetch = fetch
): Promise<T> => {
    const query = new URLSearchParams();

    if (params.page !== undefined) query.set("page", String(params.page));
    if (params.pageSize !== undefined) query.set("pageSize", String(params.pageSize));
	if (params.sortBy) query.set("sortBy", params.sortBy);
	if (params.sortDir) query.set("sortDir", params.sortDir);

    for (const [key, value] of Object.entries(filter ?? {})) {
		if (value !== undefined && value !== "") query.set(key, String(value));
	}

    const queryString = query.toString();
	const fullUrl = `${url}${queryString ? `?${queryString}` : ""}`;

	const response = await fetchFn(fullUrl);

	if (!response.ok) {
		throw await parseErrorResponse(response);
	}

	return response.json();
}
