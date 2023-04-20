import dayjs from 'dayjs';
import { ITraining } from 'app/shared/model/training.model';
import { IRequirents } from 'app/shared/model/requirents.model';
import { IJob } from 'app/shared/model/job.model';
import { TypeCourse } from 'app/shared/model/enumerations/type-course.model';

export interface ICourse {
  id?: number;
  id2Job?: number | null;
  code?: string;
  name?: string;
  expirationInMonth?: number | null;
  typeCourse?: TypeCourse | null;
  autorizationBy?: string | null;
  durationAuthorizationInMonth?: number | null;
  description?: string | null;
  link?: string | null;
  created?: string | null;
  createdAt?: string | null;
  edited?: string | null;
  editedAt?: string | null;
  reqCourses?: ICourse[] | null;
  trainings?: ITraining[] | null;
  requirents?: IRequirents[] | null;
  course?: ICourse | null;
  jobs?: IJob[] | null;
}

export const defaultValue: Readonly<ICourse> = {};
