import dayjs from 'dayjs';
import { IEvidence } from 'app/shared/model/evidence.model';
import { ICourse } from 'app/shared/model/course.model';
import { IEmployee } from 'app/shared/model/employee.model';

export interface ITraining {
  id?: number;
  id2Course?: number | null;
  id2Employee?: number | null;
  code?: string;
  date?: string | null;
  expiry?: string | null;
  evidences?: IEvidence[] | null;
  courses?: ICourse[] | null;
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<ITraining> = {};
