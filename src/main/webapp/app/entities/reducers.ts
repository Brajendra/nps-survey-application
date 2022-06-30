import campaign from 'app/entities/campaign/campaign.reducer';
import question from 'app/entities/question/question.reducer';
import answer from 'app/entities/answer/answer.reducer';
import userCampaign from 'app/entities/user-campaign/user-campaign.reducer';
import userAnswers from 'app/entities/user-answers/user-answers.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  campaign,
  question,
  answer,
  userCampaign,
  userAnswers,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
