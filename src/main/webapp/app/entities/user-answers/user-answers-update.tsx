import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IQuestion } from 'app/shared/model/question.model';
import { getEntities as getQuestions } from 'app/entities/question/question.reducer';
import { IUserAnswers } from 'app/shared/model/user-answers.model';
import { getEntity, updateEntity, createEntity, reset } from './user-answers.reducer';

export const UserAnswersUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const questions = useAppSelector(state => state.question.entities);
  const userAnswersEntity = useAppSelector(state => state.userAnswers.entity);
  const loading = useAppSelector(state => state.userAnswers.loading);
  const updating = useAppSelector(state => state.userAnswers.updating);
  const updateSuccess = useAppSelector(state => state.userAnswers.updateSuccess);
  const handleClose = () => {
    props.history.push('/user-answers');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getQuestions({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...userAnswersEntity,
      ...values,
      question: questions.find(it => it.id.toString() === values.question.toString()),
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
          ...userAnswersEntity,
          question: userAnswersEntity?.question?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="npsSurveyApp.userAnswers.home.createOrEditLabel" data-cy="UserAnswersCreateUpdateHeading">
            <Translate contentKey="npsSurveyApp.userAnswers.home.createOrEditLabel">Create or edit a UserAnswers</Translate>
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
                  id="user-answers-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('npsSurveyApp.userAnswers.answers')}
                id="user-answers-answers"
                name="answers"
                data-cy="answers"
                type="text"
              />
              <ValidatedField
                label={translate('npsSurveyApp.userAnswers.createdAt')}
                id="user-answers-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="date"
              />
              <ValidatedField
                label={translate('npsSurveyApp.userAnswers.updatedAt')}
                id="user-answers-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="date"
              />
              <ValidatedField
                id="user-answers-question"
                name="question"
                data-cy="question"
                label={translate('npsSurveyApp.userAnswers.question')}
                type="select"
              >
                <option value="" key="0" />
                {questions
                  ? questions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/user-answers" replace color="info">
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

export default UserAnswersUpdate;
