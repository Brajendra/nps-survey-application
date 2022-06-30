import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Campaign from './campaign';
import Question from './question';
import Answer from './answer';
import UserCampaign from './user-campaign';
import UserAnswers from './user-answers';
import Link from './link';
import CampaignLink from './campaign-link';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}campaign`} component={Campaign} />
        <ErrorBoundaryRoute path={`${match.url}question`} component={Question} />
        <ErrorBoundaryRoute path={`${match.url}answer`} component={Answer} />
        <ErrorBoundaryRoute path={`${match.url}user-campaign`} component={UserCampaign} />
        <ErrorBoundaryRoute path={`${match.url}user-answers`} component={UserAnswers} />
        <ErrorBoundaryRoute path={`${match.url}link`} component={Link} />
        <ErrorBoundaryRoute path={`${match.url}campaign-link`} component={CampaignLink} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
