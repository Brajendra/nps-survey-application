import dayjs from 'dayjs';
import { ICampaign } from 'app/shared/model/campaign.model';

export interface IUserCampaign {
  id?: number;
  hashCode?: string;
  attemptQuestionCount?: number | null;
  eventId?: string | null;
  eventType?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  campaign?: ICampaign | null;
}

export const defaultValue: Readonly<IUserCampaign> = {};
