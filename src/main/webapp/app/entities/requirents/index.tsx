import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Requirents from './requirents';
import RequirentsDetail from './requirents-detail';
import RequirentsUpdate from './requirents-update';
import RequirentsDeleteDialog from './requirents-delete-dialog';

const RequirentsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Requirents />} />
    <Route path="new" element={<RequirentsUpdate />} />
    <Route path=":id">
      <Route index element={<RequirentsDetail />} />
      <Route path="edit" element={<RequirentsUpdate />} />
      <Route path="delete" element={<RequirentsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RequirentsRoutes;
