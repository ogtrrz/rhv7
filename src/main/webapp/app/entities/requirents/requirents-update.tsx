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
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.editedAt = convertDateTimeToServer(values.editedAt);

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
      ? {
          createdAt: displayDefaultDateTime(),
          editedAt: displayDefaultDateTime(),
        }
      : {
          kind: 'CERTIFICATE',
          ...requirentsEntity,
          createdAt: convertDateTimeFromServer(requirentsEntity.createdAt),
          editedAt: convertDateTimeFromServer(requirentsEntity.editedAt),
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
              <ValidatedField label="Extra 1" id="requirents-extra1" name="extra1" data-cy="extra1" type="text" />
              <ValidatedField label="Extra 2" id="requirents-extra2" name="extra2" data-cy="extra2" type="text" />
              <ValidatedField label="Extra 3" id="requirents-extra3" name="extra3" data-cy="extra3" type="text" />
              <ValidatedField label="Extra 4" id="requirents-extra4" name="extra4" data-cy="extra4" type="text" />
              <ValidatedField label="Extra 5" id="requirents-extra5" name="extra5" data-cy="extra5" type="text" />
              <ValidatedField label="Extra 6" id="requirents-extra6" name="extra6" data-cy="extra6" type="text" />
              <ValidatedField label="Extra 7" id="requirents-extra7" name="extra7" data-cy="extra7" type="text" />
              <ValidatedField label="Extra 8" id="requirents-extra8" name="extra8" data-cy="extra8" type="text" />
              <ValidatedField label="Extra 9" id="requirents-extra9" name="extra9" data-cy="extra9" type="text" />
              <ValidatedField label="Extra 10" id="requirents-extra10" name="extra10" data-cy="extra10" type="text" />
              <ValidatedField label="Created" id="requirents-created" name="created" data-cy="created" type="text" />
              <ValidatedField
                label="Created At"
                id="requirents-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Edited" id="requirents-edited" name="edited" data-cy="edited" type="text" />
              <ValidatedField
                label="Edited At"
                id="requirents-editedAt"
                name="editedAt"
                data-cy="editedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
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
