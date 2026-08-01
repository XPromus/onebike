import type { PageServerLoad } from './$types';
import { error } from '@sveltejs/kit';
import { getNationDtoToNation } from '$lib/middleware/nation.mapper';
import type { GetNationDto } from '$lib/types/server/nation.types';
import { readEnum, readInt, type SortDir } from '$lib/server/api/pagination';
import { getNations, type NationListParams } from '$lib/server/api/nation';
import type { PageMeta } from '$lib/types/client/page.types';
import { ApiError } from '$lib/server/api/errors';

const DEFAULT_PAGE = 0;
const DEFAULT_PAGE_SIZE = 20;
const SORT_FIELDS = ["LONG_NAME", "SHORT_NAME"] as const;
const SORT_DIRECTIONS = ["ASCENDING", "DESCENDING"] as const;

export const load = (async ({ url, fetch }) => {
    const page: number = readInt(url.searchParams.get("page"), DEFAULT_PAGE);
    const pageSize: number = readInt(url.searchParams.get("pageSize"), DEFAULT_PAGE_SIZE);
    const sortBy: string = readEnum(url.searchParams.get("sortBy"), SORT_FIELDS, "LONG_NAME");
    const sortDir: SortDir = readEnum(url.searchParams.get("sortDir"), SORT_DIRECTIONS, "ASCENDING")

    const longName = url.searchParams.get("longName") ?? undefined;
    const shortName = url.searchParams.get("shortName") ?? undefined;

    const params: NationListParams = {
        page, pageSize, sortBy, sortDir, longName, shortName
    };

    try {
        const dto = await getNations(params, fetch);
        const content: GetNationDto[] = dto.content ?? [];
        const nations = content.map(getNationDtoToNation);
        const pageMeta: PageMeta = {
            pageNumber: dto.number ?? 0,
            pageSize: dto.size ?? pageSize,
            totalPages: dto.totalPages ?? 0,
            totalElements: dto.totalElements ?? 0,
            first: dto.first ?? true,
            last: dto.last ?? true,
        };
        return { nations, page: pageMeta };
    } catch (e) {
        if (e instanceof ApiError) {
            throw error(e.status, e.details.message ?? e.details.error);
        }

        throw e;
    }
}) satisfies PageServerLoad;
