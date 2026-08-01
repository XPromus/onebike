export type PageMeta = {
    pageNumber: number;
    pageSize: number;
    totalPages: number;
    totalElements: number;
    first: boolean;
    last: boolean;
};

export type Page<T> = {
    items: T[]
} & PageMeta;
