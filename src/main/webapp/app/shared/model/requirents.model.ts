import dayjs from 'dayjs';
import { ICourse } from 'app/shared/model/course.model';
import { Kind } from 'app/shared/model/enumerations/kind.model';

export interface IRequirents {
  id?: number;
  id2Course?: number | null;
  code?: string;
  expirationInMonth?: number | null;
  kind?: Kind | null;
  description?: string | null;
  created?: string | null;
  createdAt?: string | null;
  edited?: string | null;
  editedAt?: string | null;
  codes?: ICourse[] | null;
}

export const defaultValue: Readonly<IRequirents> = {};
