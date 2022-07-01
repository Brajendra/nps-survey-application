import dayjs from 'dayjs';
import { IUserCampaign } from 'app/shared/model/user-campaign.model';
import { ICampaign } from 'app/shared/model/campaign.model';

export interface ICampaignLink {
  id?: number;
  code?: string;
  userInfo?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  userCampaign?: IUserCampaign | null;
  campaign?: ICampaign | null;
}

export const defaultValue: Readonly<ICampaignLink> = {};
