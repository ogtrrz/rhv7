import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Job from './job';
import Employee from './employee';
import Course from './course';
import Requirents from './requirents';
import Training from './training';
import Evidence from './evidence';
import HistoricData from './historic-data';
import ToDo from './to-do';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="job/*" element={<Job />} />
        <Route path="employee/*" element={<Employee />} />
        <Route path="course/*" element={<Course />} />
        <Route path="requirents/*" element={<Requirents />} />
        <Route path="training/*" element={<Training />} />
        <Route path="evidence/*" element={<Evidence />} />
        <Route path="historic-data/*" element={<HistoricData />} />
        <Route path="to-do/*" element={<ToDo />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
