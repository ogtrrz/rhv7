import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './to-do.reducer';

export const ToDoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const toDoEntity = useAppSelector(state => state.toDo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="toDoDetailsHeading">To Do</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{toDoEntity.id}</dd>
          <dt>
            <span id="id2Employee">Id 2 Employee</span>
          </dt>
          <dd>{toDoEntity.id2Employee}</dd>
          <dt>
            <span id="date">Date</span>
          </dt>
          <dd>{toDoEntity.date ? <TextFormat value={toDoEntity.date} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{toDoEntity.description}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{toDoEntity.state}</dd>
          <dt>
            <span id="link">Link</span>
          </dt>
          <dd>{toDoEntity.link}</dd>
          <dt>
            <span id="created">Created</span>
          </dt>
          <dd>{toDoEntity.created}</dd>
          <dt>
            <span id="createdAt">Created At</span>
          </dt>
          <dd>{toDoEntity.createdAt ? <TextFormat value={toDoEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="edited">Edited</span>
          </dt>
          <dd>{toDoEntity.edited}</dd>
          <dt>
            <span id="editedAt">Edited At</span>
          </dt>
          <dd>{toDoEntity.editedAt ? <TextFormat value={toDoEntity.editedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
        </dl>
        <Button tag={Link} to="/to-do" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/to-do/${toDoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ToDoDetail;
