import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './employee.reducer';

export const EmployeeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const employeeEntity = useAppSelector(state => state.employee.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="employeeDetailsHeading">Employee</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{employeeEntity.id}</dd>
          <dt>
            <span id="id2Job">Id 2 Job</span>
          </dt>
          <dd>{employeeEntity.id2Job}</dd>
          <dt>
            <span id="user">User</span>
          </dt>
          <dd>{employeeEntity.user}</dd>
          <dt>
            <span id="firstName">First Name</span>
          </dt>
          <dd>{employeeEntity.firstName}</dd>
          <dt>
            <span id="lastName">Last Name</span>
          </dt>
          <dd>{employeeEntity.lastName}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{employeeEntity.email}</dd>
          <dt>
            <span id="phoneNumber">Phone Number</span>
          </dt>
          <dd>{employeeEntity.phoneNumber}</dd>
          <dt>
            <span id="hireDate">Hire Date</span>
          </dt>
          <dd>{employeeEntity.hireDate ? <TextFormat value={employeeEntity.hireDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="emergencyContact">Emergency Contact</span>
          </dt>
          <dd>{employeeEntity.emergencyContact}</dd>
          <dt>
            <span id="emergencyPhone">Emergency Phone</span>
          </dt>
          <dd>{employeeEntity.emergencyPhone}</dd>
          <dt>
            <span id="blodeType">Blode Type</span>
          </dt>
          <dd>{employeeEntity.blodeType}</dd>
          <dt>
            <span id="allergies">Allergies</span>
          </dt>
          <dd>{employeeEntity.allergies}</dd>
          <dt>
            <span id="birthDate">Birth Date</span>
          </dt>
          <dd>{employeeEntity.birthDate ? <TextFormat value={employeeEntity.birthDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="note">Note</span>
          </dt>
          <dd>{employeeEntity.note}</dd>
          <dt>
            <span id="extra1">Extra 1</span>
          </dt>
          <dd>{employeeEntity.extra1}</dd>
          <dt>
            <span id="extra2">Extra 2</span>
          </dt>
          <dd>{employeeEntity.extra2}</dd>
          <dt>
            <span id="extra3">Extra 3</span>
          </dt>
          <dd>{employeeEntity.extra3}</dd>
          <dt>
            <span id="extra4">Extra 4</span>
          </dt>
          <dd>{employeeEntity.extra4}</dd>
          <dt>
            <span id="extra5">Extra 5</span>
          </dt>
          <dd>{employeeEntity.extra5}</dd>
          <dt>
            <span id="extra6">Extra 6</span>
          </dt>
          <dd>{employeeEntity.extra6}</dd>
          <dt>
            <span id="extra7">Extra 7</span>
          </dt>
          <dd>{employeeEntity.extra7}</dd>
          <dt>
            <span id="extra8">Extra 8</span>
          </dt>
          <dd>{employeeEntity.extra8}</dd>
          <dt>
            <span id="extra9">Extra 9</span>
          </dt>
          <dd>{employeeEntity.extra9}</dd>
          <dt>
            <span id="extra10">Extra 10</span>
          </dt>
          <dd>{employeeEntity.extra10}</dd>
          <dt>
            <span id="created">Created</span>
          </dt>
          <dd>{employeeEntity.created}</dd>
          <dt>
            <span id="createdAt">Created At</span>
          </dt>
          <dd>{employeeEntity.createdAt ? <TextFormat value={employeeEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="edited">Edited</span>
          </dt>
          <dd>{employeeEntity.edited}</dd>
          <dt>
            <span id="editedAt">Edited At</span>
          </dt>
          <dd>{employeeEntity.editedAt ? <TextFormat value={employeeEntity.editedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>Training</dt>
          <dd>
            {employeeEntity.trainings
              ? employeeEntity.trainings.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.code}</a>
                    {employeeEntity.trainings && i === employeeEntity.trainings.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Todo</dt>
          <dd>
            {employeeEntity.todos
              ? employeeEntity.todos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.description}</a>
                    {employeeEntity.todos && i === employeeEntity.todos.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Historic Data</dt>
          <dd>
            {employeeEntity.historicData
              ? employeeEntity.historicData.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.name}</a>
                    {employeeEntity.historicData && i === employeeEntity.historicData.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Employee</dt>
          <dd>{employeeEntity.employee ? employeeEntity.employee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/employee" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employee/${employeeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmployeeDetail;
