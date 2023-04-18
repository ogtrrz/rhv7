import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/job">
        Job
      </MenuItem>
      <MenuItem icon="asterisk" to="/employee">
        Employee
      </MenuItem>
      <MenuItem icon="asterisk" to="/course">
        Course
      </MenuItem>
      <MenuItem icon="asterisk" to="/requirents">
        Requirents
      </MenuItem>
      <MenuItem icon="asterisk" to="/training">
        Training
      </MenuItem>
      <MenuItem icon="asterisk" to="/evidence">
        Evidence
      </MenuItem>
      <MenuItem icon="asterisk" to="/historic-data">
        Historic Data
      </MenuItem>
      <MenuItem icon="asterisk" to="/to-do">
        To Do
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
