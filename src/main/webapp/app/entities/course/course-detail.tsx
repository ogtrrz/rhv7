import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './course.reducer';

export const CourseDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const courseEntity = useAppSelector(state => state.course.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="courseDetailsHeading">Course</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{courseEntity.id}</dd>
          <dt>
            <span id="id2Job">Id 2 Job</span>
          </dt>
          <dd>{courseEntity.id2Job}</dd>
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{courseEntity.code}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{courseEntity.name}</dd>
          <dt>
            <span id="expirationInMonth">Expiration In Month</span>
          </dt>
          <dd>{courseEntity.expirationInMonth}</dd>
          <dt>
            <span id="typeCourse">Type Course</span>
          </dt>
          <dd>{courseEntity.typeCourse}</dd>
          <dt>
            <span id="autorizationBy">Autorization By</span>
          </dt>
          <dd>{courseEntity.autorizationBy}</dd>
          <dt>
            <span id="durationAuthorizationInMonth">Duration Authorization In Month</span>
          </dt>
          <dd>{courseEntity.durationAuthorizationInMonth}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{courseEntity.description}</dd>
          <dt>
            <span id="link">Link</span>
          </dt>
          <dd>{courseEntity.link}</dd>
          <dt>
            <span id="extra1">Extra 1</span>
          </dt>
          <dd>{courseEntity.extra1}</dd>
          <dt>
            <span id="extra2">Extra 2</span>
          </dt>
          <dd>{courseEntity.extra2}</dd>
          <dt>
            <span id="extra3">Extra 3</span>
          </dt>
          <dd>{courseEntity.extra3}</dd>
          <dt>
            <span id="extra4">Extra 4</span>
          </dt>
          <dd>{courseEntity.extra4}</dd>
          <dt>
            <span id="extra5">Extra 5</span>
          </dt>
          <dd>{courseEntity.extra5}</dd>
          <dt>
            <span id="extra6">Extra 6</span>
          </dt>
          <dd>{courseEntity.extra6}</dd>
          <dt>
            <span id="extra7">Extra 7</span>
          </dt>
          <dd>{courseEntity.extra7}</dd>
          <dt>
            <span id="extra8">Extra 8</span>
          </dt>
          <dd>{courseEntity.extra8}</dd>
          <dt>
            <span id="extra9">Extra 9</span>
          </dt>
          <dd>{courseEntity.extra9}</dd>
          <dt>
            <span id="extra10">Extra 10</span>
          </dt>
          <dd>{courseEntity.extra10}</dd>
          <dt>
            <span id="created">Created</span>
          </dt>
          <dd>{courseEntity.created}</dd>
          <dt>
            <span id="createdAt">Created At</span>
          </dt>
          <dd>{courseEntity.createdAt ? <TextFormat value={courseEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="edited">Edited</span>
          </dt>
          <dd>{courseEntity.edited}</dd>
          <dt>
            <span id="editedAt">Edited At</span>
          </dt>
          <dd>{courseEntity.editedAt ? <TextFormat value={courseEntity.editedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>Training</dt>
          <dd>
            {courseEntity.trainings
              ? courseEntity.trainings.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.code}</a>
                    {courseEntity.trainings && i === courseEntity.trainings.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Requirents</dt>
          <dd>
            {courseEntity.requirents
              ? courseEntity.requirents.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.code}</a>
                    {courseEntity.requirents && i === courseEntity.requirents.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Course</dt>
          <dd>{courseEntity.course ? courseEntity.course.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/course" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/course/${courseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CourseDetail;
