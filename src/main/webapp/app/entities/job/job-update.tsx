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
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { IJob } from 'app/shared/model/job.model';
import { Rol } from 'app/shared/model/enumerations/rol.model';
import { Handling } from 'app/shared/model/enumerations/handling.model';
import { getEntity, updateEntity, createEntity, reset } from './job.reducer';

export const JobUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const courses = useAppSelector(state => state.course.entities);
  const employees = useAppSelector(state => state.employee.entities);
  const jobEntity = useAppSelector(state => state.job.entity);
  const loading = useAppSelector(state => state.job.loading);
  const updating = useAppSelector(state => state.job.updating);
  const updateSuccess = useAppSelector(state => state.job.updateSuccess);
  const rolValues = Object.keys(Rol);
  const handlingValues = Object.keys(Handling);

  const handleClose = () => {
    navigate('/job' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCourses({}));
    dispatch(getEmployees({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...jobEntity,
      ...values,
      courses: mapIdList(values.courses),
      employees: mapIdList(values.employees),
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
          rol: 'OPERATIONAL',
          handling: 'RAMP',
          ...jobEntity,
          courses: jobEntity?.courses?.map(e => e.id.toString()),
          employees: jobEntity?.employees?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="rhv7App.job.home.createOrEditLabel" data-cy="JobCreateUpdateHeading">
            Create or edit a Job
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="job-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Job Title"
                id="job-jobTitle"
                name="jobTitle"
                data-cy="jobTitle"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField label="Rol" id="job-rol" name="rol" data-cy="rol" type="select">
                {rolValues.map(rol => (
                  <option value={rol} key={rol}>
                    {rol}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Extra 1" id="job-extra1" name="extra1" data-cy="extra1" type="text" />
              <ValidatedField label="Extra 2" id="job-extra2" name="extra2" data-cy="extra2" type="text" />
              <ValidatedField label="Extra 3" id="job-extra3" name="extra3" data-cy="extra3" type="text" />
              <ValidatedField label="Extra 4" id="job-extra4" name="extra4" data-cy="extra4" type="text" />
              <ValidatedField label="Extra 5" id="job-extra5" name="extra5" data-cy="extra5" type="text" />
              <ValidatedField label="Extra 6" id="job-extra6" name="extra6" data-cy="extra6" type="text" />
              <ValidatedField label="Extra 7" id="job-extra7" name="extra7" data-cy="extra7" type="text" />
              <ValidatedField label="Extra 8" id="job-extra8" name="extra8" data-cy="extra8" type="text" />
              <ValidatedField label="Extra 9" id="job-extra9" name="extra9" data-cy="extra9" type="text" />
              <ValidatedField label="Extra 10" id="job-extra10" name="extra10" data-cy="extra10" type="text" />
              <ValidatedField label="Handling" id="job-handling" name="handling" data-cy="handling" type="select">
                {handlingValues.map(handling => (
                  <option value={handling} key={handling}>
                    {handling}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Course" id="job-course" data-cy="course" type="select" multiple name="courses">
                <option value="" key="0" />
                {courses
                  ? courses.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.code}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField label="Employee" id="job-employee" data-cy="employee" type="select" multiple name="employees">
                <option value="" key="0" />
                {employees
                  ? employees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.user}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/job" replace color="info">
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

export default JobUpdate;
