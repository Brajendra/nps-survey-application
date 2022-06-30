import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserCampaign from './user-campaign';
import UserCampaignDetail from './user-campaign-detail';
import UserCampaignUpdate from './user-campaign-update';
import UserCampaignDeleteDialog from './user-campaign-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserCampaignUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserCampaignUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserCampaignDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserCampaign} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserCampaignDeleteDialog} />
  </>
);

export default Routes;
