import { ICourse } from 'app/shared/model/course.model';
import { IEmployee } from 'app/shared/model/employee.model';
import { Rol } from 'app/shared/model/enumerations/rol.model';
import { Handling } from 'app/shared/model/enumerations/handling.model';

export interface IJob {
  id?: number;
  jobTitle?: string;
  rol?: Rol | null;
  handling?: Handling | null;
  courses?: ICourse[] | null;
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<IJob> = {};
