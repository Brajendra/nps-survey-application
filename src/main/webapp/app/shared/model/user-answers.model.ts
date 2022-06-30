import dayjs from 'dayjs';
import { IQuestion } from 'app/shared/model/question.model';

export interface IUserAnswers {
  id?: number;
  answers?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  question?: IQuestion | null;
}

export const defaultValue: Readonly<IUserAnswers> = {};
