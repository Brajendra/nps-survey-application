export interface IReview {
  id?: number;
  rating?: number | null;
}

export const defaultValue: Readonly<IReview> = {};
