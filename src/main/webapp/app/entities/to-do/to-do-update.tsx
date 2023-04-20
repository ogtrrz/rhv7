import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { IToDo } from 'app/shared/model/to-do.model';
import { StateToDo } from 'app/shared/model/enumerations/state-to-do.model';
import { getEntity, updateEntity, createEntity, reset } from './to-do.reducer';

export const ToDoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const employees = useAppSelector(state => state.employee.entities);
  const toDoEntity = useAppSelector(state => state.toDo.entity);
  const loading = useAppSelector(state => state.toDo.loading);
  const updating = useAppSelector(state => state.toDo.updating);
  const updateSuccess = useAppSelector(state => state.toDo.updateSuccess);
  const stateToDoValues = Object.keys(StateToDo);

  const handleClose = () => {
    navigate('/to-do' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getEmployees({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.date = convertDateTimeToServer(values.date);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.editedAt = convertDateTimeToServer(values.editedAt);

    const entity = {
      ...toDoEntity,
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
          date: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          editedAt: displayDefaultDateTime(),
        }
      : {
          state: 'NEW',
          ...toDoEntity,
          date: convertDateTimeFromServer(toDoEntity.date),
          createdAt: convertDateTimeFromServer(toDoEntity.createdAt),
          editedAt: convertDateTimeFromServer(toDoEntity.editedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="rhv7App.toDo.home.createOrEditLabel" data-cy="ToDoCreateUpdateHeading">
            Create or edit a To Do
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="to-do-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Id 2 Employee" id="to-do-id2Employee" name="id2Employee" data-cy="id2Employee" type="text" />
              <ValidatedField
                label="Date"
                id="to-do-date"
                name="date"
                data-cy="date"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label="Description"
                id="to-do-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField label="State" id="to-do-state" name="state" data-cy="state" type="select">
                {stateToDoValues.map(stateToDo => (
                  <option value={stateToDo} key={stateToDo}>
                    {stateToDo}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Link" id="to-do-link" name="link" data-cy="link" type="text" />
              <ValidatedField label="Created" id="to-do-created" name="created" data-cy="created" type="text" />
              <ValidatedField
                label="Created At"
                id="to-do-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Edited" id="to-do-edited" name="edited" data-cy="edited" type="text" />
              <ValidatedField
                label="Edited At"
                id="to-do-editedAt"
                name="editedAt"
                data-cy="editedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/to-do" replace color="info">
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

export default ToDoUpdate;
