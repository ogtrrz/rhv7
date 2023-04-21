import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './historic-data.reducer';

export const HistoricDataDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const historicDataEntity = useAppSelector(state => state.historicData.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="historicDataDetailsHeading">Historic Data</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{historicDataEntity.id}</dd>
          <dt>
            <span id="id2Employee">Id 2 Employee</span>
          </dt>
          <dd>{historicDataEntity.id2Employee}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{historicDataEntity.name}</dd>
          <dt>
            <span id="link">Link</span>
          </dt>
          <dd>{historicDataEntity.link}</dd>
          <dt>
            <span id="extra1">Extra 1</span>
          </dt>
          <dd>{historicDataEntity.extra1}</dd>
          <dt>
            <span id="extra2">Extra 2</span>
          </dt>
          <dd>{historicDataEntity.extra2}</dd>
          <dt>
            <span id="extra3">Extra 3</span>
          </dt>
          <dd>{historicDataEntity.extra3}</dd>
          <dt>
            <span id="extra4">Extra 4</span>
          </dt>
          <dd>{historicDataEntity.extra4}</dd>
          <dt>
            <span id="extra5">Extra 5</span>
          </dt>
          <dd>{historicDataEntity.extra5}</dd>
          <dt>
            <span id="extra6">Extra 6</span>
          </dt>
          <dd>{historicDataEntity.extra6}</dd>
          <dt>
            <span id="extra7">Extra 7</span>
          </dt>
          <dd>{historicDataEntity.extra7}</dd>
          <dt>
            <span id="extra8">Extra 8</span>
          </dt>
          <dd>{historicDataEntity.extra8}</dd>
          <dt>
            <span id="extra9">Extra 9</span>
          </dt>
          <dd>{historicDataEntity.extra9}</dd>
          <dt>
            <span id="extra10">Extra 10</span>
          </dt>
          <dd>{historicDataEntity.extra10}</dd>
          <dt>
            <span id="created">Created</span>
          </dt>
          <dd>{historicDataEntity.created}</dd>
          <dt>
            <span id="createdAt">Created At</span>
          </dt>
          <dd>
            {historicDataEntity.createdAt ? <TextFormat value={historicDataEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="edited">Edited</span>
          </dt>
          <dd>{historicDataEntity.edited}</dd>
          <dt>
            <span id="editedAt">Edited At</span>
          </dt>
          <dd>
            {historicDataEntity.editedAt ? <TextFormat value={historicDataEntity.editedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/historic-data" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/historic-data/${historicDataEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default HistoricDataDetail;
