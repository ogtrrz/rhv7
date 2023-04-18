import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Evidence from './evidence';
import EvidenceDetail from './evidence-detail';
import EvidenceUpdate from './evidence-update';
import EvidenceDeleteDialog from './evidence-delete-dialog';

const EvidenceRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Evidence />} />
    <Route path="new" element={<EvidenceUpdate />} />
    <Route path=":id">
      <Route index element={<EvidenceDetail />} />
      <Route path="edit" element={<EvidenceUpdate />} />
      <Route path="delete" element={<EvidenceDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default EvidenceRoutes;
