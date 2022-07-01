import dayjs from 'dayjs';
import { ICampaignLink } from 'app/shared/model/campaign-link.model';

export interface IUserCampaign {
  id?: number;
  code?: string;
  attemptQuestionCount?: number | null;
  eventId?: string | null;
  eventType?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  campaignLink?: ICampaignLink | null;
}

export const defaultValue: Readonly<IUserCampaign> = {};
