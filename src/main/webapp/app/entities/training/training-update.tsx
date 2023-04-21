import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEvidence } from 'app/shared/model/evidence.model';
import { getEntities as getEvidences } from 'app/entities/evidence/evidence.reducer';
import { ICourse } from 'app/shared/model/course.model';
import { getEntities as getCourses } from 'app/entities/course/course.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { ITraining } from 'app/shared/model/training.model';
import { getEntity, updateEntity, createEntity, reset } from './training.reducer';

export const TrainingUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const evidences = useAppSelector(state => state.evidence.entities);
  const courses = useAppSelector(state => state.course.entities);
  const employees = useAppSelector(state => state.employee.entities);
  const trainingEntity = useAppSelector(state => state.training.entity);
  const loading = useAppSelector(state => state.training.loading);
  const updating = useAppSelector(state => state.training.updating);
  const updateSuccess = useAppSelector(state => state.training.updateSuccess);

  const handleClose = () => {
    navigate('/training' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getEvidences({}));
    dispatch(getCourses({}));
    dispatch(getEmployees({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.date = convertDateTimeToServer(values.date);
    values.expiry = convertDateTimeToServer(values.expiry);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.editedAt = convertDateTimeToServer(values.editedAt);

    const entity = {
      ...trainingEntity,
      ...values,
      evidences: mapIdList(values.evidences),
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
          date: displayDefaultDateTime(),
          expiry: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          editedAt: displayDefaultDateTime(),
        }
      : {
          ...trainingEntity,
          date: convertDateTimeFromServer(trainingEntity.date),
          expiry: convertDateTimeFromServer(trainingEntity.expiry),
          createdAt: convertDateTimeFromServer(trainingEntity.createdAt),
          editedAt: convertDateTimeFromServer(trainingEntity.editedAt),
          evidences: trainingEntity?.evidences?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="rhv7App.training.home.createOrEditLabel" data-cy="TrainingCreateUpdateHeading">
            Create or edit a Training
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="training-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Id 2 Course" id="training-id2Course" name="id2Course" data-cy="id2Course" type="text" />
              <ValidatedField label="Id 2 Employee" id="training-id2Employee" name="id2Employee" data-cy="id2Employee" type="text" />
              <ValidatedField
                label="Code"
                id="training-code"
                name="code"
                data-cy="code"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 20, message: 'This field cannot be longer than 20 characters.' },
                }}
              />
              <ValidatedField
                label="Date"
                id="training-date"
                name="date"
                data-cy="date"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label="Expiry"
                id="training-expiry"
                name="expiry"
                data-cy="expiry"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Extra 1" id="training-extra1" name="extra1" data-cy="extra1" type="text" />
              <ValidatedField label="Extra 2" id="training-extra2" name="extra2" data-cy="extra2" type="text" />
              <ValidatedField label="Extra 3" id="training-extra3" name="extra3" data-cy="extra3" type="text" />
              <ValidatedField label="Extra 4" id="training-extra4" name="extra4" data-cy="extra4" type="text" />
              <ValidatedField label="Extra 5" id="training-extra5" name="extra5" data-cy="extra5" type="text" />
              <ValidatedField label="Extra 6" id="training-extra6" name="extra6" data-cy="extra6" type="text" />
              <ValidatedField label="Extra 7" id="training-extra7" name="extra7" data-cy="extra7" type="text" />
              <ValidatedField label="Extra 8" id="training-extra8" name="extra8" data-cy="extra8" type="text" />
              <ValidatedField label="Extra 9" id="training-extra9" name="extra9" data-cy="extra9" type="text" />
              <ValidatedField label="Extra 10" id="training-extra10" name="extra10" data-cy="extra10" type="text" />
              <ValidatedField label="Created" id="training-created" name="created" data-cy="created" type="text" />
              <ValidatedField
                label="Created At"
                id="training-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Edited" id="training-edited" name="edited" data-cy="edited" type="text" />
              <ValidatedField
                label="Edited At"
                id="training-editedAt"
                name="editedAt"
                data-cy="editedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Evidence" id="training-evidence" data-cy="evidence" type="select" multiple name="evidences">
                <option value="" key="0" />
                {evidences
                  ? evidences.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.description}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/training" replace color="info">
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

export default TrainingUpdate;
