import type { GetRiderDto } from "$lib/types/server/rider.types";
import { env } from "$env/dynamic/private";

const getURL = (
    path: string
): string => {
    return `${env.API_BASE_URL}${path}`;
}

export const putRider = async (
    path: string
): Promise<GetRiderDto> => {
    return {
        id: 0,
        firstName: "",
        lastName: "",
        dateOfBirth: "",
        placementIds: [],
        nationId: 0,
        teamId: null
    } satisfies GetRiderDto;
    //TODO: Implement
}
