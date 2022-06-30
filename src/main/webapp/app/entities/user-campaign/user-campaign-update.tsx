import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICampaign } from 'app/shared/model/campaign.model';
import { getEntities as getCampaigns } from 'app/entities/campaign/campaign.reducer';
import { IUserCampaign } from 'app/shared/model/user-campaign.model';
import { getEntity, updateEntity, createEntity, reset } from './user-campaign.reducer';

export const UserCampaignUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const campaigns = useAppSelector(state => state.campaign.entities);
  const userCampaignEntity = useAppSelector(state => state.userCampaign.entity);
  const loading = useAppSelector(state => state.userCampaign.loading);
  const updating = useAppSelector(state => state.userCampaign.updating);
  const updateSuccess = useAppSelector(state => state.userCampaign.updateSuccess);
  const handleClose = () => {
    props.history.push('/user-campaign');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getCampaigns({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...userCampaignEntity,
      ...values,
      campaign: campaigns.find(it => it.id.toString() === values.campaign.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...userCampaignEntity,
          campaign: userCampaignEntity?.campaign?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="npsSurveyApp.userCampaign.home.createOrEditLabel" data-cy="UserCampaignCreateUpdateHeading">
            <Translate contentKey="npsSurveyApp.userCampaign.home.createOrEditLabel">Create or edit a UserCampaign</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="user-campaign-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('npsSurveyApp.userCampaign.hashCode')}
                id="user-campaign-hashCode"
                name="hashCode"
                data-cy="hashCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('npsSurveyApp.userCampaign.attemptQuestionCount')}
                id="user-campaign-attemptQuestionCount"
                name="attemptQuestionCount"
                data-cy="attemptQuestionCount"
                type="text"
              />
              <ValidatedField
                label={translate('npsSurveyApp.userCampaign.eventId')}
                id="user-campaign-eventId"
                name="eventId"
                data-cy="eventId"
                type="text"
              />
              <ValidatedField
                label={translate('npsSurveyApp.userCampaign.eventType')}
                id="user-campaign-eventType"
                name="eventType"
                data-cy="eventType"
                type="text"
              />
              <ValidatedField
                label={translate('npsSurveyApp.userCampaign.createdAt')}
                id="user-campaign-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('npsSurveyApp.userCampaign.updatedAt')}
                id="user-campaign-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                id="user-campaign-campaign"
                name="campaign"
                data-cy="campaign"
                label={translate('npsSurveyApp.userCampaign.campaign')}
                type="select"
              >
                <option value="" key="0" />
                {campaigns
                  ? campaigns.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/user-campaign" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default UserCampaignUpdate;
