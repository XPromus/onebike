export type Nation = {
    id: number,
    longName: string,
    shortName: string,
    flagEmoji: string,
};

export type NationWithIds = Nation & {
    riderIds: number[],
    cupIds: number[],
    raceIds: number[],
};

export type NationEditDto = {
    id?: number,
    longName: string,
    shortName: string,
    flagEmoji: string,
};
