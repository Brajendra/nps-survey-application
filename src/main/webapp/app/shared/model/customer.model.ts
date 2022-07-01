import { IReview } from 'app/shared/model/review.model';

export interface ICustomer {
  id?: number;
  firstName?: string | null;
  customer?: IReview | null;
}

export const defaultValue: Readonly<ICustomer> = {};
