import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './training.reducer';

export const TrainingDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const trainingEntity = useAppSelector(state => state.training.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="trainingDetailsHeading">Training</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{trainingEntity.id}</dd>
          <dt>
            <span id="id2Course">Id 2 Course</span>
          </dt>
          <dd>{trainingEntity.id2Course}</dd>
          <dt>
            <span id="id2Employee">Id 2 Employee</span>
          </dt>
          <dd>{trainingEntity.id2Employee}</dd>
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{trainingEntity.code}</dd>
          <dt>
            <span id="date">Date</span>
          </dt>
          <dd>{trainingEntity.date ? <TextFormat value={trainingEntity.date} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="expiry">Expiry</span>
          </dt>
          <dd>{trainingEntity.expiry ? <TextFormat value={trainingEntity.expiry} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="created">Created</span>
          </dt>
          <dd>{trainingEntity.created}</dd>
          <dt>
            <span id="createdAt">Created At</span>
          </dt>
          <dd>{trainingEntity.createdAt ? <TextFormat value={trainingEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="edited">Edited</span>
          </dt>
          <dd>{trainingEntity.edited}</dd>
          <dt>
            <span id="editedAt">Edited At</span>
          </dt>
          <dd>{trainingEntity.editedAt ? <TextFormat value={trainingEntity.editedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>Evidence</dt>
          <dd>
            {trainingEntity.evidences
              ? trainingEntity.evidences.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.description}</a>
                    {trainingEntity.evidences && i === trainingEntity.evidences.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/training" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/training/${trainingEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default TrainingDetail;
