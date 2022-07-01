import dayjs from 'dayjs';
import { IQuestion } from 'app/shared/model/question.model';
import { IUserCampaign } from 'app/shared/model/user-campaign.model';

export interface IUserAnswers {
  id?: number;
  answers?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  question?: IQuestion | null;
  userCampaign?: IUserCampaign | null;
}

export const defaultValue: Readonly<IUserAnswers> = {};
