export type GetNationResponse = Nation[];

export type DefaultNation = {
    longName: string,
    shortName: string,
    flagEmoji: string,
};

export type Nation = DefaultNation & {
    id: number,
    riderIds: number[],
    cupIds: number[],
    raceIds: number[],
};

export type PutNationDto = DefaultNation & {
    id: number?,
};

export type NationEditDto = DefaultNation & {
    id: number?,
};

export type PostNationExistsDto = DefaultNation;
