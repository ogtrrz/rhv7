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
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<IToDo> = {};
