import { ICourse } from 'app/shared/model/course.model';
import { IEmployee } from 'app/shared/model/employee.model';
import { Rol } from 'app/shared/model/enumerations/rol.model';
import { Handling } from 'app/shared/model/enumerations/handling.model';

export interface IJob {
  id?: number;
  jobTitle?: string;
  rol?: Rol | null;
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
  handling?: Handling | null;
  courses?: ICourse[] | null;
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<IJob> = {};
