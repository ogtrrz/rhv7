import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITraining } from 'app/shared/model/training.model';
import { getEntities as getTrainings } from 'app/entities/training/training.reducer';
import { IEvidence } from 'app/shared/model/evidence.model';
import { StateToDo } from 'app/shared/model/enumerations/state-to-do.model';
import { Kind } from 'app/shared/model/enumerations/kind.model';
import { getEntity, updateEntity, createEntity, reset } from './evidence.reducer';

export const EvidenceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const trainings = useAppSelector(state => state.training.entities);
  const evidenceEntity = useAppSelector(state => state.evidence.entity);
  const loading = useAppSelector(state => state.evidence.loading);
  const updating = useAppSelector(state => state.evidence.updating);
  const updateSuccess = useAppSelector(state => state.evidence.updateSuccess);
  const stateToDoValues = Object.keys(StateToDo);
  const kindValues = Object.keys(Kind);

  const handleClose = () => {
    navigate('/evidence' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getTrainings({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.expiration = convertDateTimeToServer(values.expiration);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.editedAt = convertDateTimeToServer(values.editedAt);

    const entity = {
      ...evidenceEntity,
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
          expiration: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          editedAt: displayDefaultDateTime(),
        }
      : {
          state: 'NEW',
          kind: 'CERTIFICATE',
          ...evidenceEntity,
          expiration: convertDateTimeFromServer(evidenceEntity.expiration),
          createdAt: convertDateTimeFromServer(evidenceEntity.createdAt),
          editedAt: convertDateTimeFromServer(evidenceEntity.editedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="rhv7App.evidence.home.createOrEditLabel" data-cy="EvidenceCreateUpdateHeading">
            Create or edit a Evidence
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="evidence-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Id 2 Trining" id="evidence-id2Trining" name="id2Trining" data-cy="id2Trining" type="text" />
              <ValidatedField
                label="Id 2 Requirents"
                id="evidence-id2Requirents"
                name="id2Requirents"
                data-cy="id2Requirents"
                type="text"
              />
              <ValidatedField label="Id 2 Course" id="evidence-id2Course" name="id2Course" data-cy="id2Course" type="text" />
              <ValidatedField label="Id 2 Employee" id="evidence-id2Employee" name="id2Employee" data-cy="id2Employee" type="text" />
              <ValidatedField label="State" id="evidence-state" name="state" data-cy="state" type="select">
                {stateToDoValues.map(stateToDo => (
                  <option value={stateToDo} key={stateToDo}>
                    {stateToDo}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Kind" id="evidence-kind" name="kind" data-cy="kind" type="select">
                {kindValues.map(kind => (
                  <option value={kind} key={kind}>
                    {kind}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Description"
                id="evidence-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 500, message: 'This field cannot be longer than 500 characters.' },
                }}
              />
              <ValidatedField
                label="Note"
                id="evidence-note"
                name="note"
                data-cy="note"
                type="text"
                validate={{
                  maxLength: { value: 500, message: 'This field cannot be longer than 500 characters.' },
                }}
              />
              <ValidatedField
                label="Expiration"
                id="evidence-expiration"
                name="expiration"
                data-cy="expiration"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Link" id="evidence-link" name="link" data-cy="link" type="text" />
              <ValidatedField label="Created" id="evidence-created" name="created" data-cy="created" type="text" />
              <ValidatedField
                label="Created At"
                id="evidence-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Edited" id="evidence-edited" name="edited" data-cy="edited" type="text" />
              <ValidatedField
                label="Edited At"
                id="evidence-editedAt"
                name="editedAt"
                data-cy="editedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/evidence" replace color="info">
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

export default EvidenceUpdate;
