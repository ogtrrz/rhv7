import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './requirents.reducer';

export const RequirentsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const requirentsEntity = useAppSelector(state => state.requirents.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="requirentsDetailsHeading">Requirents</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{requirentsEntity.id}</dd>
          <dt>
            <span id="id2Course">Id 2 Course</span>
          </dt>
          <dd>{requirentsEntity.id2Course}</dd>
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{requirentsEntity.code}</dd>
          <dt>
            <span id="expirationInMonth">Expiration In Month</span>
          </dt>
          <dd>{requirentsEntity.expirationInMonth}</dd>
          <dt>
            <span id="kind">Kind</span>
          </dt>
          <dd>{requirentsEntity.kind}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{requirentsEntity.description}</dd>
          <dt>
            <span id="extra1">Extra 1</span>
          </dt>
          <dd>{requirentsEntity.extra1}</dd>
          <dt>
            <span id="extra2">Extra 2</span>
          </dt>
          <dd>{requirentsEntity.extra2}</dd>
          <dt>
            <span id="extra3">Extra 3</span>
          </dt>
          <dd>{requirentsEntity.extra3}</dd>
          <dt>
            <span id="extra4">Extra 4</span>
          </dt>
          <dd>{requirentsEntity.extra4}</dd>
          <dt>
            <span id="extra5">Extra 5</span>
          </dt>
          <dd>{requirentsEntity.extra5}</dd>
          <dt>
            <span id="extra6">Extra 6</span>
          </dt>
          <dd>{requirentsEntity.extra6}</dd>
          <dt>
            <span id="extra7">Extra 7</span>
          </dt>
          <dd>{requirentsEntity.extra7}</dd>
          <dt>
            <span id="extra8">Extra 8</span>
          </dt>
          <dd>{requirentsEntity.extra8}</dd>
          <dt>
            <span id="extra9">Extra 9</span>
          </dt>
          <dd>{requirentsEntity.extra9}</dd>
          <dt>
            <span id="extra10">Extra 10</span>
          </dt>
          <dd>{requirentsEntity.extra10}</dd>
          <dt>
            <span id="created">Created</span>
          </dt>
          <dd>{requirentsEntity.created}</dd>
          <dt>
            <span id="createdAt">Created At</span>
          </dt>
          <dd>
            {requirentsEntity.createdAt ? <TextFormat value={requirentsEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="edited">Edited</span>
          </dt>
          <dd>{requirentsEntity.edited}</dd>
          <dt>
            <span id="editedAt">Edited At</span>
          </dt>
          <dd>
            {requirentsEntity.editedAt ? <TextFormat value={requirentsEntity.editedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/requirents" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/requirents/${requirentsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default RequirentsDetail;
