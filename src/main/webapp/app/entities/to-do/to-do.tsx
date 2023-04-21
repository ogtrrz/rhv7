import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IToDo } from 'app/shared/model/to-do.model';
import { getEntities } from './to-do.reducer';

export const ToDo = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const toDoList = useAppSelector(state => state.toDo.entities);
  const loading = useAppSelector(state => state.toDo.loading);
  const totalItems = useAppSelector(state => state.toDo.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (location.search !== endURL) {
      navigate(`${location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  return (
    <div>
      <h2 id="to-do-heading" data-cy="ToDoHeading">
        To Dos
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/to-do/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new To Do
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {toDoList && toDoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('id2Employee')}>
                  Id 2 Employee <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('date')}>
                  Date <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  Description <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('state')}>
                  State <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('link')}>
                  Link <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra1')}>
                  Extra 1 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra2')}>
                  Extra 2 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra3')}>
                  Extra 3 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra4')}>
                  Extra 4 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra5')}>
                  Extra 5 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra6')}>
                  Extra 6 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra7')}>
                  Extra 7 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra8')}>
                  Extra 8 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra9')}>
                  Extra 9 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('extra10')}>
                  Extra 10 <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('created')}>
                  Created <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  Created At <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('edited')}>
                  Edited <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('editedAt')}>
                  Edited At <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {toDoList.map((toDo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/to-do/${toDo.id}`} color="link" size="sm">
                      {toDo.id}
                    </Button>
                  </td>
                  <td>{toDo.id2Employee}</td>
                  <td>{toDo.date ? <TextFormat type="date" value={toDo.date} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{toDo.description}</td>
                  <td>{toDo.state}</td>
                  <td>{toDo.link}</td>
                  <td>{toDo.extra1}</td>
                  <td>{toDo.extra2}</td>
                  <td>{toDo.extra3}</td>
                  <td>{toDo.extra4}</td>
                  <td>{toDo.extra5}</td>
                  <td>{toDo.extra6}</td>
                  <td>{toDo.extra7}</td>
                  <td>{toDo.extra8}</td>
                  <td>{toDo.extra9}</td>
                  <td>{toDo.extra10}</td>
                  <td>{toDo.created}</td>
                  <td>{toDo.createdAt ? <TextFormat type="date" value={toDo.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{toDo.edited}</td>
                  <td>{toDo.editedAt ? <TextFormat type="date" value={toDo.editedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/to-do/${toDo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/to-do/${toDo.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/to-do/${toDo.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No To Dos found</div>
        )}
      </div>
      {totalItems ? (
        <div className={toDoList && toDoList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default ToDo;
