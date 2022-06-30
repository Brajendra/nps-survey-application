import campaign from 'app/entities/campaign/campaign.reducer';
import question from 'app/entities/question/question.reducer';
import answer from 'app/entities/answer/answer.reducer';
import userCampaign from 'app/entities/user-campaign/user-campaign.reducer';
import userAnswers from 'app/entities/user-answers/user-answers.reducer';
import link from 'app/entities/link/link.reducer';
import campaignLink from 'app/entities/campaign-link/campaign-link.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  campaign,
  question,
  answer,
  userCampaign,
  userAnswers,
  link,
  campaignLink,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
