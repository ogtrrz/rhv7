import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICourse } from 'app/shared/model/course.model';
import { getEntities as getCourses } from 'app/entities/course/course.reducer';
import { IRequirents } from 'app/shared/model/requirents.model';
import { Kind } from 'app/shared/model/enumerations/kind.model';
import { getEntity, updateEntity, createEntity, reset } from './requirents.reducer';

export const RequirentsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const courses = useAppSelector(state => state.course.entities);
  const requirentsEntity = useAppSelector(state => state.requirents.entity);
  const loading = useAppSelector(state => state.requirents.loading);
  const updating = useAppSelector(state => state.requirents.updating);
  const updateSuccess = useAppSelector(state => state.requirents.updateSuccess);
  const kindValues = Object.keys(Kind);

  const handleClose = () => {
    navigate('/requirents' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCourses({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...requirentsEntity,
      ...values,
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
          kind: 'CERTIFICATE',
          ...requirentsEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="rhv7App.requirents.home.createOrEditLabel" data-cy="RequirentsCreateUpdateHeading">
            Create or edit a Requirents
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="requirents-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Id 2 Course" id="requirents-id2Course" name="id2Course" data-cy="id2Course" type="text" />
              <ValidatedField
                label="Code"
                id="requirents-code"
                name="code"
                data-cy="code"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 20, message: 'This field cannot be longer than 20 characters.' },
                }}
              />
              <ValidatedField
                label="Expiration In Month"
                id="requirents-expirationInMonth"
                name="expirationInMonth"
                data-cy="expirationInMonth"
                type="text"
              />
              <ValidatedField label="Kind" id="requirents-kind" name="kind" data-cy="kind" type="select">
                {kindValues.map(kind => (
                  <option value={kind} key={kind}>
                    {kind}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Description" id="requirents-description" name="description" data-cy="description" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/requirents" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default RequirentsUpdate;
