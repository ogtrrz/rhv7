import job from 'app/entities/job/job.reducer';
import employee from 'app/entities/employee/employee.reducer';
import course from 'app/entities/course/course.reducer';
import requirents from 'app/entities/requirents/requirents.reducer';
import training from 'app/entities/training/training.reducer';
import evidence from 'app/entities/evidence/evidence.reducer';
import historicData from 'app/entities/historic-data/historic-data.reducer';
import toDo from 'app/entities/to-do/to-do.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  job,
  employee,
  course,
  requirents,
  training,
  evidence,
  historicData,
  toDo,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
