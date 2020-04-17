export enum Direction {
  ASC = "ASC",
  DESC = "DESC"
}

export interface PageRequest {
  page: number;
  pageSize: number;
  sortBy: string;
  direction: Direction
  filter: Map<string, string>;
}

