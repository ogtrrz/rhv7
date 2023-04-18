import { ICourse } from 'app/shared/model/course.model';
import { Kind } from 'app/shared/model/enumerations/kind.model';

export interface IRequirents {
  id?: number;
  id2Course?: number | null;
  code?: string;
  expirationInMonth?: number | null;
  kind?: Kind | null;
  description?: string | null;
  codes?: ICourse[] | null;
}

export const defaultValue: Readonly<IRequirents> = {};
