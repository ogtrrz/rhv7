import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './evidence.reducer';

export const EvidenceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const evidenceEntity = useAppSelector(state => state.evidence.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="evidenceDetailsHeading">Evidence</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{evidenceEntity.id}</dd>
          <dt>
            <span id="id2Trining">Id 2 Trining</span>
          </dt>
          <dd>{evidenceEntity.id2Trining}</dd>
          <dt>
            <span id="id2Requirents">Id 2 Requirents</span>
          </dt>
          <dd>{evidenceEntity.id2Requirents}</dd>
          <dt>
            <span id="id2Course">Id 2 Course</span>
          </dt>
          <dd>{evidenceEntity.id2Course}</dd>
          <dt>
            <span id="id2Employee">Id 2 Employee</span>
          </dt>
          <dd>{evidenceEntity.id2Employee}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{evidenceEntity.state}</dd>
          <dt>
            <span id="kind">Kind</span>
          </dt>
          <dd>{evidenceEntity.kind}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{evidenceEntity.description}</dd>
          <dt>
            <span id="note">Note</span>
          </dt>
          <dd>{evidenceEntity.note}</dd>
          <dt>
            <span id="expiration">Expiration</span>
          </dt>
          <dd>
            {evidenceEntity.expiration ? <TextFormat value={evidenceEntity.expiration} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="link">Link</span>
          </dt>
          <dd>{evidenceEntity.link}</dd>
          <dt>
            <span id="extra1">Extra 1</span>
          </dt>
          <dd>{evidenceEntity.extra1}</dd>
          <dt>
            <span id="extra2">Extra 2</span>
          </dt>
          <dd>{evidenceEntity.extra2}</dd>
          <dt>
            <span id="extra3">Extra 3</span>
          </dt>
          <dd>{evidenceEntity.extra3}</dd>
          <dt>
            <span id="extra4">Extra 4</span>
          </dt>
          <dd>{evidenceEntity.extra4}</dd>
          <dt>
            <span id="extra5">Extra 5</span>
          </dt>
          <dd>{evidenceEntity.extra5}</dd>
          <dt>
            <span id="extra6">Extra 6</span>
          </dt>
          <dd>{evidenceEntity.extra6}</dd>
          <dt>
            <span id="extra7">Extra 7</span>
          </dt>
          <dd>{evidenceEntity.extra7}</dd>
          <dt>
            <span id="extra8">Extra 8</span>
          </dt>
          <dd>{evidenceEntity.extra8}</dd>
          <dt>
            <span id="extra9">Extra 9</span>
          </dt>
          <dd>{evidenceEntity.extra9}</dd>
          <dt>
            <span id="extra10">Extra 10</span>
          </dt>
          <dd>{evidenceEntity.extra10}</dd>
          <dt>
            <span id="created">Created</span>
          </dt>
          <dd>{evidenceEntity.created}</dd>
          <dt>
            <span id="createdAt">Created At</span>
          </dt>
          <dd>{evidenceEntity.createdAt ? <TextFormat value={evidenceEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="edited">Edited</span>
          </dt>
          <dd>{evidenceEntity.edited}</dd>
          <dt>
            <span id="editedAt">Edited At</span>
          </dt>
          <dd>{evidenceEntity.editedAt ? <TextFormat value={evidenceEntity.editedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
        </dl>
        <Button tag={Link} to="/evidence" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/evidence/${evidenceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default EvidenceDetail;
