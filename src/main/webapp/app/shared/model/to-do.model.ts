import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';
import { StateToDo } from 'app/shared/model/enumerations/state-to-do.model';

export interface IToDo {
  id?: number;
  id2Employee?: number | null;
  date?: string | null;
  description?: string;
  state?: StateToDo | null;
  link?: string | null;
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
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<IToDo> = {};
