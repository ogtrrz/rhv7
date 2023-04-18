import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ToDo from './to-do';
import ToDoDetail from './to-do-detail';
import ToDoUpdate from './to-do-update';
import ToDoDeleteDialog from './to-do-delete-dialog';

const ToDoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ToDo />} />
    <Route path="new" element={<ToDoUpdate />} />
    <Route path=":id">
      <Route index element={<ToDoDetail />} />
      <Route path="edit" element={<ToDoUpdate />} />
      <Route path="delete" element={<ToDoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ToDoRoutes;
