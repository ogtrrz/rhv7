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
  extra1?: string | null;
  extra2?: string | null;
  extra3?: string | null;
  extra4?: string | null;
  extra5?: string | null;
  extra6?: string | null;
  extra7?: string | null;
  extra8?: string | null;
  extra9?: string | null;
  extra10?: string | null;
  created?: string | null;
  createdAt?: string | null;
  edited?: string | null;
  editedAt?: string | null;
  evidences?: IEvidence[] | null;
  courses?: ICourse[] | null;
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<ITraining> = {};
