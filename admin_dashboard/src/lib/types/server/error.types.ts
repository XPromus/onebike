export type ErrorResponse = {
    status: number,
    error: string,
    message: string | null,
    path: string,
    timestamp: string,
};
