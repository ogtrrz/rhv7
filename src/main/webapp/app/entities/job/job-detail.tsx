import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './job.reducer';

export const JobDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const jobEntity = useAppSelector(state => state.job.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="jobDetailsHeading">Job</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{jobEntity.id}</dd>
          <dt>
            <span id="jobTitle">Job Title</span>
          </dt>
          <dd>{jobEntity.jobTitle}</dd>
          <dt>
            <span id="rol">Rol</span>
          </dt>
          <dd>{jobEntity.rol}</dd>
          <dt>
            <span id="extra1">Extra 1</span>
          </dt>
          <dd>{jobEntity.extra1}</dd>
          <dt>
            <span id="extra2">Extra 2</span>
          </dt>
          <dd>{jobEntity.extra2}</dd>
          <dt>
            <span id="extra3">Extra 3</span>
          </dt>
          <dd>{jobEntity.extra3}</dd>
          <dt>
            <span id="extra4">Extra 4</span>
          </dt>
          <dd>{jobEntity.extra4}</dd>
          <dt>
            <span id="extra5">Extra 5</span>
          </dt>
          <dd>{jobEntity.extra5}</dd>
          <dt>
            <span id="extra6">Extra 6</span>
          </dt>
          <dd>{jobEntity.extra6}</dd>
          <dt>
            <span id="extra7">Extra 7</span>
          </dt>
          <dd>{jobEntity.extra7}</dd>
          <dt>
            <span id="extra8">Extra 8</span>
          </dt>
          <dd>{jobEntity.extra8}</dd>
          <dt>
            <span id="extra9">Extra 9</span>
          </dt>
          <dd>{jobEntity.extra9}</dd>
          <dt>
            <span id="extra10">Extra 10</span>
          </dt>
          <dd>{jobEntity.extra10}</dd>
          <dt>
            <span id="handling">Handling</span>
          </dt>
          <dd>{jobEntity.handling}</dd>
          <dt>Course</dt>
          <dd>
            {jobEntity.courses
              ? jobEntity.courses.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.code}</a>
                    {jobEntity.courses && i === jobEntity.courses.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Employee</dt>
          <dd>
            {jobEntity.employees
              ? jobEntity.employees.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.user}</a>
                    {jobEntity.employees && i === jobEntity.employees.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/job" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/job/${jobEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default JobDetail;
