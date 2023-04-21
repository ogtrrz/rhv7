import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { ITraining } from 'app/shared/model/training.model';
import { getEntities as getTrainings } from 'app/entities/training/training.reducer';
import { IToDo } from 'app/shared/model/to-do.model';
import { getEntities as getToDos } from 'app/entities/to-do/to-do.reducer';
import { IHistoricData } from 'app/shared/model/historic-data.model';
import { getEntities as getHistoricData } from 'app/entities/historic-data/historic-data.reducer';
import { IJob } from 'app/shared/model/job.model';
import { getEntities as getJobs } from 'app/entities/job/job.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntity, updateEntity, createEntity, reset } from './employee.reducer';

export const EmployeeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const employees = useAppSelector(state => state.employee.entities);
  const trainings = useAppSelector(state => state.training.entities);
  const toDos = useAppSelector(state => state.toDo.entities);
  const historicData = useAppSelector(state => state.historicData.entities);
  const jobs = useAppSelector(state => state.job.entities);
  const employeeEntity = useAppSelector(state => state.employee.entity);
  const loading = useAppSelector(state => state.employee.loading);
  const updating = useAppSelector(state => state.employee.updating);
  const updateSuccess = useAppSelector(state => state.employee.updateSuccess);

  const handleClose = () => {
    navigate('/employee' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getEmployees({}));
    dispatch(getTrainings({}));
    dispatch(getToDos({}));
    dispatch(getHistoricData({}));
    dispatch(getJobs({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.hireDate = convertDateTimeToServer(values.hireDate);
    values.birthDate = convertDateTimeToServer(values.birthDate);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.editedAt = convertDateTimeToServer(values.editedAt);

    const entity = {
      ...employeeEntity,
      ...values,
      trainings: mapIdList(values.trainings),
      todos: mapIdList(values.todos),
      historicData: mapIdList(values.historicData),
      employee: employees.find(it => it.id.toString() === values.employee.toString()),
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
          hireDate: displayDefaultDateTime(),
          birthDate: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          editedAt: displayDefaultDateTime(),
        }
      : {
          ...employeeEntity,
          hireDate: convertDateTimeFromServer(employeeEntity.hireDate),
          birthDate: convertDateTimeFromServer(employeeEntity.birthDate),
          createdAt: convertDateTimeFromServer(employeeEntity.createdAt),
          editedAt: convertDateTimeFromServer(employeeEntity.editedAt),
          trainings: employeeEntity?.trainings?.map(e => e.id.toString()),
          todos: employeeEntity?.todos?.map(e => e.id.toString()),
          historicData: employeeEntity?.historicData?.map(e => e.id.toString()),
          employee: employeeEntity?.employee?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="rhv7App.employee.home.createOrEditLabel" data-cy="EmployeeCreateUpdateHeading">
            Create or edit a Employee
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="employee-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Id 2 Job" id="employee-id2Job" name="id2Job" data-cy="id2Job" type="text" />
              <ValidatedField
                label="User"
                id="employee-user"
                name="user"
                data-cy="user"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField
                label="First Name"
                id="employee-firstName"
                name="firstName"
                data-cy="firstName"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField
                label="Last Name"
                id="employee-lastName"
                name="lastName"
                data-cy="lastName"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField label="Email" id="employee-email" name="email" data-cy="email" type="text" />
              <ValidatedField label="Phone Number" id="employee-phoneNumber" name="phoneNumber" data-cy="phoneNumber" type="text" />
              <ValidatedField
                label="Hire Date"
                id="employee-hireDate"
                name="hireDate"
                data-cy="hireDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label="Emergency Contact"
                id="employee-emergencyContact"
                name="emergencyContact"
                data-cy="emergencyContact"
                type="text"
                validate={{
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField
                label="Emergency Phone"
                id="employee-emergencyPhone"
                name="emergencyPhone"
                data-cy="emergencyPhone"
                type="text"
              />
              <ValidatedField
                label="Blode Type"
                id="employee-blodeType"
                name="blodeType"
                data-cy="blodeType"
                type="text"
                validate={{
                  maxLength: { value: 2, message: 'This field cannot be longer than 2 characters.' },
                }}
              />
              <ValidatedField
                label="Allergies"
                id="employee-allergies"
                name="allergies"
                data-cy="allergies"
                type="text"
                validate={{
                  maxLength: { value: 500, message: 'This field cannot be longer than 500 characters.' },
                }}
              />
              <ValidatedField
                label="Birth Date"
                id="employee-birthDate"
                name="birthDate"
                data-cy="birthDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label="Note"
                id="employee-note"
                name="note"
                data-cy="note"
                type="text"
                validate={{
                  maxLength: { value: 2000, message: 'This field cannot be longer than 2000 characters.' },
                }}
              />
              <ValidatedField label="Extra 1" id="employee-extra1" name="extra1" data-cy="extra1" type="text" />
              <ValidatedField label="Extra 2" id="employee-extra2" name="extra2" data-cy="extra2" type="text" />
              <ValidatedField label="Extra 3" id="employee-extra3" name="extra3" data-cy="extra3" type="text" />
              <ValidatedField label="Extra 4" id="employee-extra4" name="extra4" data-cy="extra4" type="text" />
              <ValidatedField label="Extra 5" id="employee-extra5" name="extra5" data-cy="extra5" type="text" />
              <ValidatedField label="Extra 6" id="employee-extra6" name="extra6" data-cy="extra6" type="text" />
              <ValidatedField label="Extra 7" id="employee-extra7" name="extra7" data-cy="extra7" type="text" />
              <ValidatedField label="Extra 8" id="employee-extra8" name="extra8" data-cy="extra8" type="text" />
              <ValidatedField label="Extra 9" id="employee-extra9" name="extra9" data-cy="extra9" type="text" />
              <ValidatedField label="Extra 10" id="employee-extra10" name="extra10" data-cy="extra10" type="text" />
              <ValidatedField label="Created" id="employee-created" name="created" data-cy="created" type="text" />
              <ValidatedField
                label="Created At"
                id="employee-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Edited" id="employee-edited" name="edited" data-cy="edited" type="text" />
              <ValidatedField
                label="Edited At"
                id="employee-editedAt"
                name="editedAt"
                data-cy="editedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Training" id="employee-training" data-cy="training" type="select" multiple name="trainings">
                <option value="" key="0" />
                {trainings
                  ? trainings.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.code}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField label="Todo" id="employee-todo" data-cy="todo" type="select" multiple name="todos">
                <option value="" key="0" />
                {toDos
                  ? toDos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.description}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label="Historic Data"
                id="employee-historicData"
                data-cy="historicData"
                type="select"
                multiple
                name="historicData"
              >
                <option value="" key="0" />
                {historicData
                  ? historicData.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="employee-employee" name="employee" data-cy="employee" label="Employee" type="select">
                <option value="" key="0" />
                {employees
                  ? employees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/employee" replace color="info">
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

export default EmployeeUpdate;
