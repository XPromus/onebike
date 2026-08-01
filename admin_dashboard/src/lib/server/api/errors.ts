import type { ErrorResponse } from "$lib/types/server/error.types";

export class ApiError extends Error {
    status: number;
    details: ErrorResponse;

    constructor(
        status: number, 
        details: ErrorResponse
    ) {
        super(details.message ?? details.error ?? "Request failed!");
        this.name = "ApiError";
        this.status = status;
        this.details = details;
    }
}

const isErrorResponse = (value: unknown): value is ErrorResponse => {
    return typeof value === "object" && value !== null &&
    typeof (value as ErrorResponse).status === "number" &&
    typeof (value as ErrorResponse).error === "string";
}

export const parseErrorResponse = async (response: Response): Promise<ApiError> => {
    try {
        const body: unknown = await response.json();
        if (isErrorResponse(body)) return new ApiError(response.status, body);
    } catch {
        // Body is not JSON
    }

    return new ApiError(response.status, {
        status: response.status,
        error: response.statusText || "Unknown Error",
        message: null,
        path: new URL(response.url).pathname,
        timestamp: new Date().toISOString()
    });
}
