import dayjs from 'dayjs';
import { ICampaign } from 'app/shared/model/campaign.model';

export interface ILink {
  id?: number;
  code?: string;
  userInfo?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  campaign?: ICampaign | null;
}

export const defaultValue: Readonly<ILink> = {};
