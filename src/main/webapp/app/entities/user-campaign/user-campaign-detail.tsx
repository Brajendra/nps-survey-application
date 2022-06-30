import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './user-campaign.reducer';

export const UserCampaignDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const userCampaignEntity = useAppSelector(state => state.userCampaign.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userCampaignDetailsHeading">
          <Translate contentKey="npsSurveyApp.userCampaign.detail.title">UserCampaign</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{userCampaignEntity.id}</dd>
          <dt>
            <span id="hashCode">
              <Translate contentKey="npsSurveyApp.userCampaign.hashCode">Hash Code</Translate>
            </span>
          </dt>
          <dd>{userCampaignEntity.hashCode}</dd>
          <dt>
            <span id="attemptQuestionCount">
              <Translate contentKey="npsSurveyApp.userCampaign.attemptQuestionCount">Attempt Question Count</Translate>
            </span>
          </dt>
          <dd>{userCampaignEntity.attemptQuestionCount}</dd>
          <dt>
            <span id="eventId">
              <Translate contentKey="npsSurveyApp.userCampaign.eventId">Event Id</Translate>
            </span>
          </dt>
          <dd>{userCampaignEntity.eventId}</dd>
          <dt>
            <span id="eventType">
              <Translate contentKey="npsSurveyApp.userCampaign.eventType">Event Type</Translate>
            </span>
          </dt>
          <dd>{userCampaignEntity.eventType}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="npsSurveyApp.userCampaign.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {userCampaignEntity.createdAt ? (
              <TextFormat value={userCampaignEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="npsSurveyApp.userCampaign.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {userCampaignEntity.updatedAt ? (
              <TextFormat value={userCampaignEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="npsSurveyApp.userCampaign.campaign">Campaign</Translate>
          </dt>
          <dd>{userCampaignEntity.campaign ? userCampaignEntity.campaign.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/user-campaign" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-campaign/${userCampaignEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default UserCampaignDetail;
