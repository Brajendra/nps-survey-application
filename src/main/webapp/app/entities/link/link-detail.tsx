import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './link.reducer';

export const LinkDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const linkEntity = useAppSelector(state => state.link.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="linkDetailsHeading">
          <Translate contentKey="npsSurveyApp.link.detail.title">Link</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{linkEntity.id}</dd>
          <dt>
            <span id="hashCode">
              <Translate contentKey="npsSurveyApp.link.hashCode">Hash Code</Translate>
            </span>
          </dt>
          <dd>{linkEntity.hashCode}</dd>
          <dt>
            <span id="userInfo">
              <Translate contentKey="npsSurveyApp.link.userInfo">User Info</Translate>
            </span>
          </dt>
          <dd>{linkEntity.userInfo}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="npsSurveyApp.link.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{linkEntity.createdAt ? <TextFormat value={linkEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="npsSurveyApp.link.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{linkEntity.updatedAt ? <TextFormat value={linkEntity.updatedAt} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="npsSurveyApp.link.campaign">Campaign</Translate>
          </dt>
          <dd>{linkEntity.campaign ? linkEntity.campaign.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/link" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/link/${linkEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LinkDetail;
