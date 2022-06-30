import dayjs from 'dayjs';
import { IAnswer } from 'app/shared/model/answer.model';
import { IUserAnswers } from 'app/shared/model/user-answers.model';
import { ICampaign } from 'app/shared/model/campaign.model';
import { QuestionType } from 'app/shared/model/enumerations/question-type.model';

export interface IQuestion {
  id?: number;
  title?: string;
  type?: QuestionType;
  order?: number | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  answers?: IAnswer[] | null;
  userAnswer?: IUserAnswers | null;
  campaign?: ICampaign | null;
}

export const defaultValue: Readonly<IQuestion> = {};
