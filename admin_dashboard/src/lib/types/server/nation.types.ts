export type GetNationDto = {
    id: number,
    longName: string,
    shortName: string,
    flagEmoji: string,
}

export type GetNationWithChildrenDto = {
    id: number,
    longName: string,
    shortName: string,
    flagEmoji: string,
    riderIds: number[],
    cupIds: number[],
    raceIds: number[],
}

export type NationDescriptorDto = {
    id: number,
    longName: string,
    shortName: string,
    flagEmoji: string,
};

export type PostNationExistsDto = {
    longName: string,
    shortName: string,
    flagEmoji: string,
};

export type PutNationDto = {
    id?: number,
    longName: string,
    shortName: string,
    flagEmoji: string,
}

export type GetNationResponse = GetNationDto[];
