import dayjs from 'dayjs';
import { IUserAnswers } from 'app/shared/model/user-answers.model';
import { ICampaignLink } from 'app/shared/model/campaign-link.model';

export interface IUserCampaign {
  id?: number;
  code?: string;
  attemptQuestionCount?: number | null;
  eventId?: string | null;
  eventType?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  userAnswers?: IUserAnswers[] | null;
  campaignLink?: ICampaignLink | null;
}

export const defaultValue: Readonly<IUserCampaign> = {};
