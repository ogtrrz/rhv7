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
            <span id="description">Description</span>
          </dt>
          <dd>{evidenceEntity.description}</dd>
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
