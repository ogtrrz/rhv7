import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';

export interface IHistoricData {
  id?: number;
  id2Employee?: number | null;
  name?: string;
  link?: string | null;
  created?: string | null;
  createdAt?: string | null;
  edited?: string | null;
  editedAt?: string | null;
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<IHistoricData> = {};
