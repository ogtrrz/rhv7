import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getCourses } from 'app/entities/course/course.reducer';
import { ITraining } from 'app/shared/model/training.model';
import { getEntities as getTrainings } from 'app/entities/training/training.reducer';
import { IRequirents } from 'app/shared/model/requirents.model';
import { getEntities as getRequirents } from 'app/entities/requirents/requirents.reducer';
import { IJob } from 'app/shared/model/job.model';
import { getEntities as getJobs } from 'app/entities/job/job.reducer';
import { ICourse } from 'app/shared/model/course.model';
import { TypeCourse } from 'app/shared/model/enumerations/type-course.model';
import { getEntity, updateEntity, createEntity, reset } from './course.reducer';

export const CourseUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const courses = useAppSelector(state => state.course.entities);
  const trainings = useAppSelector(state => state.training.entities);
  const requirents = useAppSelector(state => state.requirents.entities);
  const jobs = useAppSelector(state => state.job.entities);
  const courseEntity = useAppSelector(state => state.course.entity);
  const loading = useAppSelector(state => state.course.loading);
  const updating = useAppSelector(state => state.course.updating);
  const updateSuccess = useAppSelector(state => state.course.updateSuccess);
  const typeCourseValues = Object.keys(TypeCourse);

  const handleClose = () => {
    navigate('/course' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCourses({}));
    dispatch(getTrainings({}));
    dispatch(getRequirents({}));
    dispatch(getJobs({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...courseEntity,
      ...values,
      trainings: mapIdList(values.trainings),
      requirents: mapIdList(values.requirents),
      course: courses.find(it => it.id.toString() === values.course.toString()),
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
          typeCourse: 'PRESENT',
          ...courseEntity,
          trainings: courseEntity?.trainings?.map(e => e.id.toString()),
          requirents: courseEntity?.requirents?.map(e => e.id.toString()),
          course: courseEntity?.course?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="rhv7App.course.home.createOrEditLabel" data-cy="CourseCreateUpdateHeading">
            Create or edit a Course
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="course-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Id 2 Job" id="course-id2Job" name="id2Job" data-cy="id2Job" type="text" />
              <ValidatedField
                label="Code"
                id="course-code"
                name="code"
                data-cy="code"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 20, message: 'This field cannot be longer than 20 characters.' },
                }}
              />
              <ValidatedField
                label="Name"
                id="course-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField
                label="Expiration In Month"
                id="course-expirationInMonth"
                name="expirationInMonth"
                data-cy="expirationInMonth"
                type="text"
              />
              <ValidatedField label="Type Course" id="course-typeCourse" name="typeCourse" data-cy="typeCourse" type="select">
                {typeCourseValues.map(typeCourse => (
                  <option value={typeCourse} key={typeCourse}>
                    {typeCourse}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Autorization By"
                id="course-autorizationBy"
                name="autorizationBy"
                data-cy="autorizationBy"
                type="text"
                validate={{
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField
                label="Duration Authorization In Month"
                id="course-durationAuthorizationInMonth"
                name="durationAuthorizationInMonth"
                data-cy="durationAuthorizationInMonth"
                type="text"
              />
              <ValidatedField
                label="Description"
                id="course-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  maxLength: { value: 500, message: 'This field cannot be longer than 500 characters.' },
                }}
              />
              <ValidatedField label="Link" id="course-link" name="link" data-cy="link" type="text" />
              <ValidatedField label="Training" id="course-training" data-cy="training" type="select" multiple name="trainings">
                <option value="" key="0" />
                {trainings
                  ? trainings.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.code}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField label="Requirents" id="course-requirents" data-cy="requirents" type="select" multiple name="requirents">
                <option value="" key="0" />
                {requirents
                  ? requirents.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.code}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="course-course" name="course" data-cy="course" label="Course" type="select">
                <option value="" key="0" />
                {courses
                  ? courses.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/course" replace color="info">
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

export default CourseUpdate;
