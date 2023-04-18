import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import HistoricData from './historic-data';
import HistoricDataDetail from './historic-data-detail';
import HistoricDataUpdate from './historic-data-update';
import HistoricDataDeleteDialog from './historic-data-delete-dialog';

const HistoricDataRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<HistoricData />} />
    <Route path="new" element={<HistoricDataUpdate />} />
    <Route path=":id">
      <Route index element={<HistoricDataDetail />} />
      <Route path="edit" element={<HistoricDataUpdate />} />
      <Route path="delete" element={<HistoricDataDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default HistoricDataRoutes;
