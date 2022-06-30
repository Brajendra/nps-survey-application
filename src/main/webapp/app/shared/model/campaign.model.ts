import dayjs from 'dayjs';
import { ILink } from 'app/shared/model/link.model';
import { IQuestion } from 'app/shared/model/question.model';
import { IUserCampaign } from 'app/shared/model/user-campaign.model';
import { ActorType } from 'app/shared/model/enumerations/actor-type.model';
import { EventType } from 'app/shared/model/enumerations/event-type.model';
import { ChannelType } from 'app/shared/model/enumerations/channel-type.model';

export interface ICampaign {
  id?: number;
  name?: string;
  isActive?: boolean | null;
  createdBy?: string | null;
  startDate?: string | null;
  endDate?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  actor?: ActorType | null;
  eventType?: EventType | null;
  channel?: ChannelType | null;
  links?: ILink[] | null;
  questions?: IQuestion[] | null;
  userCampaign?: IUserCampaign | null;
}

export const defaultValue: Readonly<ICampaign> = {
  isActive: false,
};
